package com.example.clinicmanagerfront.presentation.view.profileScreen

import androidx.compose.foundation.layout.Arrangement
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
            .padding(horizontal = 17.5.dp),
        verticalArrangement = Arrangement.spacedBy(17.5.dp)
    ) {
        Spacer(modifier = Modifier.size(0.dp))
        PersonalCard()
        ProfileStats()
        ProfileButtons()
    }
}
