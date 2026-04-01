package com.example.clinicmanagerfront.presentation.view.mainScreen.uiState

data class MainUiState (
    val messageResponse: String = "",
    val patientCount: Int = 0,
    val appointmentCount: Int = 0,
    val doctorsCount: Int = 0,
    val completedCount: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)