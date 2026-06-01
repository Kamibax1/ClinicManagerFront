package com.example.clinicmanagerfront.presentation.view.adminPanelScreen.userCard

import androidx.compose.ui.graphics.Color

data class UserDataCard(
    val id: Long,
    val username: String,
    val email: String,
    val enabled: String,
    val role: String,
    val buttonText: String,
    val buttonColor: Color
)