package com.sahyadri.samrakshane.data.repository

import android.content.Context
import android.content.pm.PackageManager
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class DefaultLocationTrackerTest {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var context: Context
    private lateinit var locationTracker: DefaultLocationTracker

    @Before
    fun setup() {
        fusedLocationProviderClient = mock(FusedLocationProviderClient::class.java)
        context = mock(Context::class.java)
        locationTracker = DefaultLocationTracker(fusedLocationProviderClient, context)
    }

    @Test
    fun `getCurrentLocation returns null when permission denied`() = runTest {
        // Arrange
        `when`(context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION))
            .thenReturn(PackageManager.PERMISSION_DENIED)
        `when`(context.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION))
            .thenReturn(PackageManager.PERMISSION_DENIED)

        // Act
        val result = locationTracker.getCurrentLocation()

        // Assert
        assertNull(result)
    }
}
