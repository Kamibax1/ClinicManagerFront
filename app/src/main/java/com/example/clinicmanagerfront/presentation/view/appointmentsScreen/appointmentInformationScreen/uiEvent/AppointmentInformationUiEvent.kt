package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.uiEvent

import com.example.clinicmanagerfront.data.model.UserResponse
import com.example.clinicmanagerfront.data.model.enums.StatusEnum

sealed class AppointmentInformationUiEvent {
    data class UpdateStatus(val status: StatusEnum) : AppointmentInformationUiEvent()
    data class UpdateSymptoms(val symptoms: String) : AppointmentInformationUiEvent()
}