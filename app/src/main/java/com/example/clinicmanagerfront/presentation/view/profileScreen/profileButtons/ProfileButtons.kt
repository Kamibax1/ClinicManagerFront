package com.example.clinicmanagerfront.presentation.view.profileScreen.profileButtons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun ProfileButtons() {
    val buttons = listOf<ProfileButtonData>(
        ProfileButtonData(
            Icons.Outlined.Settings,
            Gray700,
            "Настройки",
            Gray900,
            Gray400,
            Gray50
        ),
        ProfileButtonData(
            Icons.Outlined.PersonOutline,
            Gray700,
            "Редактировать профиль",
            Gray900,
            Gray400,
            Gray50
        ),
        ProfileButtonData(
            Icons.AutoMirrored.Outlined.Logout,
            Red600,
            "Редактировать профиль",
            Red600,
            Red400,
            Red50
        )
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        buttons.forEach { button ->
            ProfileButton(button)
        }
    }
}