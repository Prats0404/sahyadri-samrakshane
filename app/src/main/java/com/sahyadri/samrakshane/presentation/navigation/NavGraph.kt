package com.sahyadri.samrakshane.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sahyadri.samrakshane.domain.model.AlertType
import com.sahyadri.samrakshane.presentation.screens.AlertSelectionScreen
import com.sahyadri.samrakshane.presentation.screens.CaptureScreen
import com.sahyadri.samrakshane.presentation.screens.HomeScreen
import com.sahyadri.samrakshane.presentation.screens.HistoryScreen
import com.sahyadri.samrakshane.presentation.screens.ReviewSubmissionScreen
import com.sahyadri.samrakshane.presentation.screens.LoginScreen
import com.sahyadri.samrakshane.presentation.screens.RegisterScreen
import java.net.URLDecoder

@Composable
fun SahyadriNavGraph(
    navController: NavHostController,
    startDestination: String,
    userId: String,
    onAuthSuccess: (String) -> Unit,
    onLogout: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = onAuthSuccess,
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        composable(route = Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = onAuthSuccess,
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.Home.route) {
            HomeScreen(
                userId = userId,
                onReportAlertClick = {
                    navController.navigate(Screen.AlertSelection.route)
                },
                onHistoryClick = {
                    navController.navigate(Screen.History.route)
                },
                onLogoutClick = onLogout
            )
        }

        composable(route = Screen.AlertSelection.route) {
            AlertSelectionScreen(
                onCategorySelected = { alertType ->
                    navController.navigate(Screen.Capture.createRoute(alertType.name))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.Capture.route,
            arguments = listOf(navArgument("alertType") { type = NavType.StringType })
        ) { backStackEntry ->
            val alertType = backStackEntry.arguments?.getString("alertType") ?: AlertType.WILDLIFE.name
            CaptureScreen(
                onPhotoCaptured = { photoFile, location ->
                    val lat = location?.latitude ?: 0.0
                    val lon = location?.longitude ?: 0.0
                    navController.navigate(Screen.ReviewSubmission.createRoute(alertType, photoFile.absolutePath, lat, lon))
                }
            )
        }

        composable(
            route = Screen.ReviewSubmission.route,
            arguments = listOf(
                navArgument("alertType") { type = NavType.StringType },
                navArgument("photoPath") { type = NavType.StringType },
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lon") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val alertType = backStackEntry.arguments?.getString("alertType") ?: AlertType.WILDLIFE.name
            val encodedPath = backStackEntry.arguments?.getString("photoPath") ?: ""
            val photoPath = URLDecoder.decode(encodedPath, "UTF-8")
            val lat = backStackEntry.arguments?.getFloat("lat")?.toDouble() ?: 0.0
            val lon = backStackEntry.arguments?.getFloat("lon")?.toDouble() ?: 0.0

            ReviewSubmissionScreen(
                alertType = AlertType.valueOf(alertType),
                photoPath = photoPath,
                latitude = lat,
                longitude = lon,
                userId = userId,
                onSubmissionComplete = {
                    navController.popBackStack(Screen.Home.route, inclusive = false)
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = Screen.History.route) {
            HistoryScreen(userId = userId)
        }
    }
}
