package com.example.clinicmanagerfront.presentation.view.profileScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.presentation.view.profileScreen.profileButtons.ProfileButtons
import com.example.clinicmanagerfront.presentation.view.profileScreen.profileStats.ProfileStats

@Composable
fun ProfileScreen(navController: NavHostController) {

    val verticalScroll = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(verticalScroll)
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 32.dp
            )
    ) {
        Spacer(modifier = Modifier.size(17.5.dp))
        PersonalCard()
        Spacer(modifier = Modifier.size(24.dp))
        ProfileStats()
        Spacer(modifier = Modifier.size(24.dp))
        ProfileButtons()
    }
}
