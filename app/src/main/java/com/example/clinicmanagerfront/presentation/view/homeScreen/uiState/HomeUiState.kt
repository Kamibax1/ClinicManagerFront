package com.example.clinicmanagerfront.presentation.view.homeScreen.uiState

data class HomeUiState (
    val countAppointmentsToday: Int = 0,
    val countPatients: Int = 0,
    val countDoctors: Int = 0,
    val countAppointmentsCompleted: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)