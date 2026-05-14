package com.sahyadri.samrakshane

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.sahyadri.samrakshane.presentation.navigation.SahyadriNavGraph
import com.sahyadri.samrakshane.presentation.navigation.Screen
import com.sahyadri.samrakshane.presentation.ui.theme.SahyadriTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var currentUser by remember { mutableStateOf(FirebaseAuth.getInstance().currentUser) }

            DisposableEffect(Unit) {
                val listener = FirebaseAuth.AuthStateListener { auth ->
                    currentUser = auth.currentUser
                }
                FirebaseAuth.getInstance().addAuthStateListener(listener)
                onDispose {
                    FirebaseAuth.getInstance().removeAuthStateListener(listener)
                }
            }

            val startDestination = if (currentUser != null) Screen.Home.route else Screen.Login.route
            val userId = currentUser?.uid ?: ""

            SahyadriTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    SahyadriNavGraph(
                        navController = navController,
                        startDestination = startDestination,
                        userId = userId,
                        onAuthSuccess = { uid ->
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                                popUpTo(Screen.Register.route) { inclusive = true }
                            }
                        },
                        onLogout = {
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Home.route) { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}
