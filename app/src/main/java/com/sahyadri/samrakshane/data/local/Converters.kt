package com.sahyadri.samrakshane.data.local

import androidx.room.TypeConverter
import com.sahyadri.samrakshane.domain.model.AlertStatus
import com.sahyadri.samrakshane.domain.model.AlertType
import com.sahyadri.samrakshane.domain.model.SyncStatus

class Converters {
    @TypeConverter
    fun fromAlertType(value: AlertType): String = value.name

    @TypeConverter
    fun toAlertType(value: String): AlertType = enumValueOf(value)

    @TypeConverter
    fun fromAlertStatus(value: AlertStatus): String = value.name

    @TypeConverter
    fun toAlertStatus(value: String): AlertStatus = enumValueOf(value)

    @TypeConverter
    fun fromSyncStatus(value: SyncStatus): String = value.name

    @TypeConverter
    fun toSyncStatus(value: String): SyncStatus = enumValueOf(value)
}
