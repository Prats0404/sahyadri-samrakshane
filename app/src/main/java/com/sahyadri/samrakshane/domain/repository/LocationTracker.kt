package com.sahyadri.samrakshane.domain.repository

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationTracker {
    fun getLocationUpdates(): Flow<Location>
    suspend fun getCurrentLocation(): Location?
}
