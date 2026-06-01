package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.uiState

import com.example.clinicmanagerfront.data.model.UserResponse
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.AppointmentInformationData
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.StatusCardData

data class AppointmentInformationUiState(
    val appointment: AppointmentInformationData? = null,
    val statusCard: StatusCardData? = null,
    val symptoms: String = "",
    val user: UserResponse? = null,
    val doctorId: Long? = null,
    val accessForDoctor: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)