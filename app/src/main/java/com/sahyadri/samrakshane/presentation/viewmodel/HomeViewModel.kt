package com.sahyadri.samrakshane.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _myReportsCount = MutableStateFlow(0)
    val myReportsCount: StateFlow<Int> = _myReportsCount.asStateFlow()

    private val _localAlertsCount = MutableStateFlow(0)
    val localAlertsCount: StateFlow<Int> = _localAlertsCount.asStateFlow()

    private var isListening = false

    fun loadStats(userId: String) {
        if (isListening) return
        isListening = true

        // Real-time listener for total alerts
        firestore.collection("alerts").addSnapshotListener { snapshot, error ->
            if (snapshot != null) {
                _localAlertsCount.value = snapshot.size()
            }
        }
        
        // Real-time listener for user's reports
        firestore.collection("alerts").whereEqualTo("userId", userId).addSnapshotListener { snapshot, error ->
            if (snapshot != null) {
                _myReportsCount.value = snapshot.size()
            }
        }
    }
}
