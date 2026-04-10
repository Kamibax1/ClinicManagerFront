package com.example.clinicmanagerfront.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.AppointmentsScreen
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.AppointmentInformationScreen
import com.example.clinicmanagerfront.presentation.view.doctorScreen.DoctorsScreen
import com.example.clinicmanagerfront.presentation.view.doctorScreen.doctorCard.DoctorCard
import com.example.clinicmanagerfront.presentation.view.homeScreen.HomeScreen
import com.example.clinicmanagerfront.presentation.view.patientsScreen.PatientsScreen
import com.example.clinicmanagerfront.presentation.view.profileScreen.ProfileScreen

@Composable
fun Navigation(
    navController: NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route){
            HomeScreen(navController)
        }
        composable(Screen.Appointments.route) {
            AppointmentsScreen(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(Screen.Patients.route) {
            PatientsScreen()
        }
        composable(Screen.Doctors.route) {
            DoctorsScreen()
        }
        composable(Screen.AppointmentInformation.route) {
            AppointmentInformationScreen()
        }
    }
}
