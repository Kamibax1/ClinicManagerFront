package com.example.clinicmanagerfront

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.example.clinicmanagerfront.navigation.Navigation
import com.example.clinicmanagerfront.ui.theme.ClinicManagerFrontTheme

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
