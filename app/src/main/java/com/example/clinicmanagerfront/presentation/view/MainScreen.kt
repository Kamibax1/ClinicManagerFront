package com.example.clinicmanagerfront.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.clinicmanagerfront.navigation.Navigation
import com.example.clinicmanagerfront.navigation.Screen
import com.example.clinicmanagerfront.presentation.view.common.BottomNavigationBar
import com.example.clinicmanagerfront.presentation.view.common.Header

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val showScaffold = currentRoute !in listOf(Screen.SignIn.route, Screen.SignUp.route, Screen.AdminPanel.route)
    val currentTitle = getScreenTitle(currentRoute)
    val currentIcon = getScreenIcon(currentRoute)

    if (showScaffold) {
        Scaffold(
            topBar = {
                Header(currentTitle, currentIcon, false, navController)
            },
            bottomBar = {
                BottomNavigationBar(navController)
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Navigation(navController = navController)
            }
        }
    } else if(currentRoute == Screen.AdminPanel.route) {
        Scaffold(
            topBar = {
                Header(currentTitle, currentIcon, true, navController)
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Navigation(navController = navController)
            }
        }
    } else {
        Navigation(navController = navController)
    }
}

private fun getScreenIcon(route: String?): ImageVector {
    return when (route) {
        Screen.Home.route -> Screen.Home.icon
        Screen.Appointments.route -> Screen.Appointments.icon
        Screen.Profile.route -> Screen.Profile.icon
        Screen.Patients.route -> Screen.Patients.icon
        Screen.Doctors.route -> Screen.Doctors.icon
        Screen.AdminPanel.route -> Screen.AdminPanel.icon
        Screen.Test.route -> Screen.Test.icon
        else -> {
            if (route?.startsWith("appointment_information_screen/") == true) {
                Screen.AppointmentInformation.icon
            } else Icons.Default.Close
        }
    }
}
private fun getScreenTitle(route: String?): String {
    return when (route) {
        Screen.Home.route -> Screen.Home.title
        Screen.Appointments.route -> Screen.Appointments.title
        Screen.Profile.route -> Screen.Profile.title
        Screen.Patients.route -> Screen.Patients.title
        Screen.Doctors.route -> Screen.Doctors.title
        Screen.AdminPanel.route -> Screen.AdminPanel.title
        Screen.Test.route -> Screen.Test.title
        else -> {
            if (route?.startsWith("appointment_information_screen/") == true) {
                Screen.AppointmentInformation.title
            } else ""
        }
    }
}
