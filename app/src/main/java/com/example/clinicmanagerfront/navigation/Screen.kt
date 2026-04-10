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
    object Patients : Screen("patients_screen", "Пациенты", Icons.Default.Person)
    object Doctors : Screen("doctors_screen", "Врачи", Icons.Default.Person)
    object AppointmentInformation: Screen("appointment_information_screen", "Иноформация о записи", Icons.Default.Description)
}
