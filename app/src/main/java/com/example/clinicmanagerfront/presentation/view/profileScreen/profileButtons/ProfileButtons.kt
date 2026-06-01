package com.example.clinicmanagerfront.presentation.view.profileScreen.profileButtons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.presentation.view.profileScreen.uiState.ProfileUiState
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun ProfileButtons(
    onOpenForm: () -> Unit,
    onLogout: () -> Unit,
    onOpenScreen: () -> Unit,
    uiState: ProfileUiState
) {
    val buttons = mutableListOf(
        ProfileButtonData(
            Icons.Outlined.Settings,
            Gray700,
            "Настройки",
            Gray900,
            Gray400,
            Gray50,
            onOpenScreen
        ),
        ProfileButtonData(
            Icons.AutoMirrored.Outlined.Logout,
            Red600,
            "Выйти из аккаунта",
            Red600,
            Red400,
            Red50,
            onLogout
        )
    )
    if (uiState.user?.role != RoleEnum.ADMIN){
        buttons.add(
            index = 1,
            element = ProfileButtonData(
                Icons.Outlined.PersonOutline,
                Gray700,
                "Редактировать профиль",
                Gray900,
                Gray400,
                Gray50,
                onOpenForm
        ))
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        buttons.forEach { button ->
            ProfileButton(button)
        }
    }
}
