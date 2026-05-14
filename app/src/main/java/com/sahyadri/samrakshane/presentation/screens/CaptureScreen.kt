package com.sahyadri.samrakshane.presentation.screens

import android.Manifest
import android.content.Context
import android.location.Location
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.sahyadri.samrakshane.presentation.viewmodel.CaptureViewModel
import java.io.File

@Composable
fun CaptureScreen(
    viewModel: CaptureViewModel = hiltViewModel(),
    onPhotoCaptured: (File, Location?) -> Unit
) {
    val context = LocalContext.current
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE)
        }
    }
    
    var hasPermissions by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        hasPermissions = permissions[Manifest.permission.CAMERA] == true &&
                         (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true || 
                          permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true)
        if (hasPermissions) {
            viewModel.startLocationTracking()
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    if (!hasPermissions) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Permissions required for Camera and Location")
        }
        return
    }

    val currentLocation by viewModel.currentLocation.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        CameraPreview(controller = controller, modifier = Modifier.fillMaxSize())
        
        // GPS Overlay
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
                .padding(bottom = 100.dp) // Leave space for capture button
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(8.dp)
        ) {
            Text(
                text = "GPS Coordinates (±5m accuracy)",
                color = Color.White,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = currentLocation?.let { "Lat: ${it.latitude}\nLon: ${it.longitude}" } ?: "Acquiring GPS lock...",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Capture Button
        Button(
            onClick = { 
                takePhoto(context, controller, currentLocation, onPhotoCaptured) 
            },
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .size(72.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
        ) {
            // Empty content, just a circular button
        }
    }
}

private fun takePhoto(
    context: Context,
    controller: LifecycleCameraController,
    location: Location?,
    onPhotoCaptured: (File, Location?) -> Unit
) {
    val photoFile = File(context.cacheDir, "alert_image_${System.currentTimeMillis()}.jpg")
    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    controller.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                onPhotoCaptured(photoFile, location)
            }

            override fun onError(exc: ImageCaptureException) {
                Toast.makeText(context, "Photo capture failed", Toast.LENGTH_SHORT).show()
                exc.printStackTrace()
            }
        }
    )
}
