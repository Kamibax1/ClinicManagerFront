package com.example.clinicmanagerfront.presentation.view.homeScreen.uiState

import com.example.clinicmanagerfront.data.model.DoctorShortInfoResponse
import com.example.clinicmanagerfront.data.model.PatientShortInfoResponse

data class HomeFormAppointmentUiState(
    val patients: List<PatientShortInfoResponse>? = null,
    val doctors: List<DoctorShortInfoResponse>? = null,
    val data: String? = null,
    val time: String? = null,
    val symptoms: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)