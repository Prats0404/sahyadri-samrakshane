package com.sahyadri.samrakshane.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sahyadri.samrakshane.data.local.dao.AlertDao
import com.sahyadri.samrakshane.data.local.entity.AlertEntity

@Database(entities = [AlertEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SahyadriDatabase : RoomDatabase() {
    abstract fun alertDao(): AlertDao
}
