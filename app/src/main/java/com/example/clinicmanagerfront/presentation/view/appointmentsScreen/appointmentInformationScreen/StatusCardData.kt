package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen

import androidx.compose.ui.graphics.Color
import com.example.clinicmanagerfront.data.model.enums.StatusEnum

data class StatusCardData(
    val text: StatusEnum,
    val textColor: Color,
    val bgColor: Color
)