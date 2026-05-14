package com.sahyadri.samrakshane.domain.repository

import com.sahyadri.samrakshane.data.local.entity.AlertEntity
import kotlinx.coroutines.flow.Flow

interface AlertRepository {
    fun getAlertsRealtime(userId: String): Flow<List<AlertEntity>>
}
