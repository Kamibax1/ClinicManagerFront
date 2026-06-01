package com.example.clinicmanagerfront.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.clinicmanagerfront.presentation.view.adminPanelScreen.AdminPanelScreen
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.AppointmentsScreen
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.AppointmentInformationScreen
import com.example.clinicmanagerfront.presentation.view.authScreens.signInScreen.SignInScreen
import com.example.clinicmanagerfront.presentation.view.authScreens.signUpScreen.SignUpScreen
import com.example.clinicmanagerfront.presentation.view.doctorScreen.DoctorsScreen
import com.example.clinicmanagerfront.presentation.view.homeScreen.HomeScreen
import com.example.clinicmanagerfront.presentation.view.patientsScreen.PatientsScreen
import com.example.clinicmanagerfront.presentation.view.profileScreen.ProfileScreen

@Composable
fun Navigation(
    navController: NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = Screen.SignIn.route
    ) {
        composable(Screen.SignIn.route) {
            SignInScreen(navController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(Screen.AdminPanel.route) {
            AdminPanelScreen()
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(Screen.Appointments.route) {
            AppointmentsScreen(navController)
        }
        composable(Screen.Patients.route) {
            PatientsScreen()
        }
        composable(Screen.Doctors.route) {
            DoctorsScreen()
        }
        composable(
            route = Screen.AppointmentInformation.route,
            arguments = listOf(navArgument("appointmentId") { type = NavType.LongType })
        ) { backStackEntry ->
            val appointmentId = Screen.AppointmentInformation.getAppointmentId(backStackEntry)
            AppointmentInformationScreen(
                navController = navController,
                appointmentId = appointmentId
            )
        }
    }
}
