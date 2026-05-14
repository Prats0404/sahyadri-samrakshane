package com.sahyadri.samrakshane.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object AlertSelection : Screen("alert_selection")
    object Capture : Screen("capture/{alertType}") {
        fun createRoute(alertType: String) = "capture/$alertType"
    }
    object ReviewSubmission : Screen("review/{alertType}/{photoPath}/{lat}/{lon}") {
        fun createRoute(alertType: String, photoPath: String, lat: Double, lon: Double) = 
            "review/$alertType/${java.net.URLEncoder.encode(photoPath, "UTF-8")}/$lat/$lon"
    }
    object History : Screen("history")
}
