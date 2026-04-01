package com.example.clinicmanagerfront.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.clinicmanagerfront.presentation.view.mainScreen.MainScreen
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun Navigation(
    navController: NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route){
            MainScreen()
        }
    }
}
