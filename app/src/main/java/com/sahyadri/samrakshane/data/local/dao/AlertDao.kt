package com.sahyadri.samrakshane.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sahyadri.samrakshane.data.local.entity.AlertEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlertDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlert(alert: AlertEntity)

    @Update
    suspend fun updateAlert(alert: AlertEntity)

    @Query("SELECT * FROM alerts ORDER BY timestamp DESC")
    fun getAllAlerts(): Flow<List<AlertEntity>>

    @Query("SELECT * FROM alerts WHERE syncStatus = 'PENDING'")
    suspend fun getPendingAlerts(): List<AlertEntity>
    
    @Query("UPDATE alerts SET syncStatus = 'SYNCED' WHERE alertId = :id")
    suspend fun markAsSynced(id: String)
}
