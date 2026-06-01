package com.example.clinicmanagerfront.presentation.view.homeScreen.uiState

import com.example.clinicmanagerfront.data.model.DoctorShortInformationModel
import com.example.clinicmanagerfront.data.model.PatientShortInformationModel
import com.example.clinicmanagerfront.data.model.UserResponse

data class HomeUiState (
    val countAppointmentsToday: Int = 0,
    val countPatients: Int = 0,
    val countDoctors: Int = 0,
    val countAppointmentsCompleted: Int = 0,
    val textDateNow: String = "",
    val isLoading: Boolean = false,
    val isLoadingStats: Boolean = false,
    val showModalScreen: Boolean = false,
    val error: String? = null,
    val user: UserResponse? = null,
    val patient: PatientShortInformationModel? = null,
    val doctor: DoctorShortInformationModel? = null
)