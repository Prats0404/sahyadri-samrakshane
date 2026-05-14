package com.sahyadri.samrakshane.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sahyadri.samrakshane.domain.model.AlertStatus
import com.sahyadri.samrakshane.domain.model.AlertType
import com.sahyadri.samrakshane.domain.model.SyncStatus
import kotlinx.serialization.Serializable

@Entity(tableName = "alerts")
@Serializable
data class AlertEntity(
    @PrimaryKey
    val alertId: String = "",
    val userId: String = "",
    val alertType: AlertType = AlertType.WILDLIFE,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val photoUrl: String = "",
    val description: String? = null,
    val status: AlertStatus = AlertStatus.REPORTED,
    val timestamp: Long = 0L,
    val syncStatus: SyncStatus = SyncStatus.PENDING
)
