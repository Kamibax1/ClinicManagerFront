package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.uiState

import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.AppointmentInformationData
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.StatusCardData

data class AppointmentInformationUiState(
    val appointment: AppointmentInformationData? = null,
    val statusCard: StatusCardData? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)