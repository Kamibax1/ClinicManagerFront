package com.example.clinicmanagerfront.presentation.view.homeScreen.uiState

import com.example.clinicmanagerfront.data.model.DoctorModel
import com.example.clinicmanagerfront.data.model.PatientModel

data class HomeFormAppointmentUiState(
    val patients: List<PatientModel>? = null,
    val doctors: List<DoctorModel>? = null,
    val data: String? = null,
    val time: String? = null,
    val symptoms: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)