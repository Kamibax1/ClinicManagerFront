package com.example.clinicmanagerfront.presentation.view.homeScreen.uiState

import com.example.clinicmanagerfront.data.model.PatientModel

data class HomeSearchUiState(
    val patients: List<PatientModel> = emptyList(),
    val patientCount: Int = 0,
    val appointmentCount: Int = 0,
    val doctorsCount: Int = 0,
    val completedCount: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)