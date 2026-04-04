package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.uiState

import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentDataCard

data class AppointmentsUiState (
    val cards: MutableList<AppointmentDataCard>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)