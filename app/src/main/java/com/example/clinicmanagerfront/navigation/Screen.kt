package com.example.clinicmanagerfront.navigation

sealed class Screen (
    val route: String,
    val title: String
) {
    object Main : Screen("main_screen", "Главный экран")
}
