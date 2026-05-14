package com.sahyadri.samrakshane.presentation.viewmodel

import android.location.Location
import com.sahyadri.samrakshane.domain.repository.LocationTracker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class CaptureViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var locationTracker: LocationTracker
    private lateinit var viewModel: CaptureViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        locationTracker = mock(LocationTracker::class.java)
        viewModel = CaptureViewModel(locationTracker)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `startLocationTracking updates currentLocation`() = runTest {
        // Arrange
        val mockLocation = mock(Location::class.java)
        `when`(locationTracker.getLocationUpdates()).thenReturn(flowOf(mockLocation))

        // Act
        viewModel.startLocationTracking()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(mockLocation, viewModel.currentLocation.value)
    }
}
