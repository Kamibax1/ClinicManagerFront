package com.example.clinicmanagerfront.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.navigation.Navigation
import com.example.clinicmanagerfront.presentation.view.common.BottomNavigationBar
import com.example.clinicmanagerfront.presentation.view.common.Header

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            Header(stringResource(R.string.app_name))
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Navigation(navController = navController)
        }
    }
}