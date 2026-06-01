package com.example.clinicmanagerfront.presentation.view.profileScreen.uiState

import com.example.clinicmanagerfront.data.model.DoctorFullInformationModel
import com.example.clinicmanagerfront.data.model.PatientFullInformationForUpdatePatientModel
import com.example.clinicmanagerfront.data.model.UserResponse

data class ProfileUiState(
    val user: UserResponse? = null,
    val countAppointment: Int? = null,
    val countCurrentAppointment: Int? = null,
    val dateRegister: String? = null,
    val patient: PatientFullInformationForUpdatePatientModel? = null,
    val doctor: DoctorFullInformationModel? = null,
    val isLoading: Boolean = false,
    val isLoadingStats: Boolean = false,
    val showLogoutDialog: Boolean = false,
    val onOpenForm: Boolean = false,
    val error: String? = null
)