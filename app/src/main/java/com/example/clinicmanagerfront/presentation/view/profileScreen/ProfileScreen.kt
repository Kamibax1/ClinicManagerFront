package com.example.clinicmanagerfront.presentation.view.profileScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.presentation.view.profileScreen.profileButtons.ProfileButtons
import com.example.clinicmanagerfront.presentation.view.profileScreen.profileStats.ProfileStats

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {

    val verticalScroll = rememberScrollState()
    var onOpenForm by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .verticalScroll(verticalScroll)
            .fillMaxSize()
            .padding(horizontal = 17.5.dp),
        verticalArrangement = Arrangement.spacedBy(17.5.dp)
    ) {
        Spacer(modifier = Modifier.size(0.dp))
        PersonalCard(onOpenForm = { onOpenForm = true })
        ProfileStats()
        ProfileButtons(
            onOpenForm = { onOpenForm = true }
        )
        Spacer(modifier = Modifier.size(0.dp))
    }

    if (onOpenForm) {
        BasicAlertDialog(
            onDismissRequest = { onOpenForm = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            UpdateProfileInformationForm(
                onDismiss = { onOpenForm = false },
                onConfirm = { onOpenForm = false }
            )
        }
    }
}
