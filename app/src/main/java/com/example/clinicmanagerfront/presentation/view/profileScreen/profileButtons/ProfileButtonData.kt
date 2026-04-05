package com.example.clinicmanagerfront.presentation.view.profileScreen.profileButtons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class ProfileButtonData(
    val icon: ImageVector,
    val iconColor: Color,
    val text: String,
    val textColor: Color,
    val secondIconColor: Color,
    val backgroundColor: Color
)