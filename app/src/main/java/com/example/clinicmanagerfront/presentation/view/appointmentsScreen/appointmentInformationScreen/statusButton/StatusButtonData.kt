package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.statusButton

import androidx.compose.ui.graphics.Color

data class StatusButtonData(
    val text: String,
    val textColor: Color,
    val backgroundColor: Color,
    val onClick: () -> Unit
)