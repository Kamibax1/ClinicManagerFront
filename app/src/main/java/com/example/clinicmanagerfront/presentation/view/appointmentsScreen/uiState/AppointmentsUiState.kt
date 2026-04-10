package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.uiState

import com.example.clinicmanagerfront.data.model.AppointmentFullModel
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentGroup
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentDataCard

data class AppointmentsUiState (
    val appointments: List<AppointmentFullModel>? = null,
    val cards: List<AppointmentDataCard>? = null,
    val groupedCards: List<AppointmentGroup>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)