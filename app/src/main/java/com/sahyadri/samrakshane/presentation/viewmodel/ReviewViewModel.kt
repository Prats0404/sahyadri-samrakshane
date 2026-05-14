package com.sahyadri.samrakshane.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.sahyadri.samrakshane.data.local.dao.AlertDao
import com.sahyadri.samrakshane.data.local.entity.AlertEntity
import com.sahyadri.samrakshane.data.remote.worker.AlertSyncWorker
import com.sahyadri.samrakshane.domain.model.AlertStatus
import com.sahyadri.samrakshane.domain.model.AlertType
import com.sahyadri.samrakshane.domain.model.SyncStatus
import com.sahyadri.samrakshane.domain.repository.GenAiRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val genAiRepository: GenAiRepository,
    private val alertDao: AlertDao,
    private val firestore: FirebaseFirestore,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _analysisResult = MutableStateFlow<String?>(null)
    val analysisResult: StateFlow<String?> = _analysisResult.asStateFlow()
    
    private val _isAnalyzing = MutableStateFlow(false)
    val isAnalyzing: StateFlow<Boolean> = _isAnalyzing.asStateFlow()

    fun analyzePhoto(photoPath: String) {
        viewModelScope.launch {
            _isAnalyzing.value = true
            val bitmap = android.graphics.BitmapFactory.decodeFile(photoPath)
            if (bitmap != null) {
                val result = genAiRepository.analyzePhoto(bitmap)
                _analysisResult.value = result.structuredDescription
            } else {
                _analysisResult.value = "Failed to load image."
            }
            _isAnalyzing.value = false
        }
    }

    fun submitAlert(
        userId: String,
        alertType: AlertType,
        photoPath: String,
        description: String,
        latitude: Double,
        longitude: Double,
        onComplete: () -> Unit
    ) {
        viewModelScope.launch {
            val alert = AlertEntity(
                alertId = UUID.randomUUID().toString(),
                userId = userId,
                alertType = alertType,
                latitude = latitude,
                longitude = longitude,
                photoUrl = photoPath,
                description = description,
                status = AlertStatus.REPORTED,
                timestamp = System.currentTimeMillis(),
                syncStatus = SyncStatus.SYNCED
            )
            
            // Insert into local DB for redundancy
            alertDao.insertAlert(alert)

            // Write directly to Firestore!
            // Firestore's offline caching ensures this is saved locally instantly,
            // immediately triggering the snapshot listeners in HomeViewModel/HistoryViewModel,
            // and then automatically pushes to the server in the background when network is available.
            firestore.collection("alerts").document(alert.alertId).set(alert)

            onComplete()
        }
    }
}
