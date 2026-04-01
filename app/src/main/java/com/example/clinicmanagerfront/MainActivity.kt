package com.example.clinicmanagerfront

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.clinicmanagerfront.navigation.Navigation
import com.example.clinicmanagerfront.ui.theme.ClinicManagerFrontTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClinicManagerFrontTheme {
                val navController = rememberNavController()
                Navigation(navController)
            }
        }
    }
}
