package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.uiState

import com.example.clinicmanagerfront.data.model.AppointmentShortInformationModel
import com.example.clinicmanagerfront.data.model.UserResponse
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentGroup
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentDataCard

data class AppointmentsUiState (
    val appointments: List<AppointmentShortInformationModel> = emptyList(),
    val filteredAppointments: List<AppointmentShortInformationModel> = emptyList(),
    val cards: List<AppointmentDataCard>? = null,
    val groupedCards: List<AppointmentGroup>? = null,
    val statusTitles: List<String> = emptyList(),
    val selectedSpecializationIndex: Int = -1,
    val searchText: String = "",
    val user: UserResponse? = null,
    val patientId: Long? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)