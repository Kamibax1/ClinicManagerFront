package com.example.clinicmanagerfront.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry

sealed class Screen (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : Screen("home_screen", "Главный экран", Icons.Default.Home)
    object SignIn : Screen("sign_in_screen", "Вход", Icons.Default.AppRegistration)
    object SignUp : Screen("sign_up_screen", "Регистрация", Icons.Default.AppRegistration)
    object Appointments : Screen("appointments_screen", "Записи", Icons.Default.CalendarToday)
    object Profile : Screen("profile_screen", "Профиль", Icons.Default.Person)
    object Patients : Screen("patients_screen", "Пациенты", Icons.Default.Person)
    object Doctors : Screen("doctors_screen", "Врачи", Icons.Default.Person)
    object AdminPanel : Screen("admin_panel_screen", "Админ Панель", Icons.Default.AdminPanelSettings)
    object Test : Screen("test_screen", "Test", Icons.Default.Person)
    object AppointmentInformation : Screen("appointment_information_screen/{appointmentId}", "Информация о записи", Icons.Default.Description) {
        fun createRoute(appointmentId: Long) = "appointment_information_screen/$appointmentId"

        fun getAppointmentId(backStackEntry: NavBackStackEntry): Long {
            return backStackEntry.arguments?.getLong("appointmentId") ?: -1L
        }
    }
}
