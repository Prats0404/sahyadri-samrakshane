package com.sahyadri.samrakshane.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahyadri.samrakshane.data.local.entity.AlertEntity
import com.sahyadri.samrakshane.domain.repository.AlertRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val alertRepository: AlertRepository
) : ViewModel() {

    private val _alerts = MutableStateFlow<List<AlertEntity>>(emptyList())
    val alerts: StateFlow<List<AlertEntity>> = _alerts.asStateFlow()

    fun loadAlerts(userId: String) {
        viewModelScope.launch {
            alertRepository.getAlertsRealtime(userId).collect { newAlerts ->
                _alerts.value = newAlerts.sortedByDescending { it.timestamp }
            }
        }
    }
}
