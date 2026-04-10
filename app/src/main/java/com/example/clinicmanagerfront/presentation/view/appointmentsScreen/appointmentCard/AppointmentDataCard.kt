package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard

import androidx.compose.ui.graphics.Color

data class AppointmentDataCard(
    val date: String,
    val time: String,
    val doctorName: String,
    val doctorSpecializations: String,
    val status: String,
    val statusColor: Color,
    val statusTextColor: Color
)
