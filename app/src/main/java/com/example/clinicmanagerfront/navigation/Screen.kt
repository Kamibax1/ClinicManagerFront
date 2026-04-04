package com.example.clinicmanagerfront.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Main : Screen("main_screen", "Главный экран", Icons.Default.Home)
    object Appointments : Screen("appointments_screen", "Записи", Icons.Default.CalendarToday)
    object Profile : Screen("profile_screen", "Профиль", Icons.Default.Person)
}
