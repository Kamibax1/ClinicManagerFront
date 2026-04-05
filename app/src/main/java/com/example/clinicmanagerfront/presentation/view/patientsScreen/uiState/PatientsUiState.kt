package com.example.clinicmanagerfront.presentation.view.patientsScreen.uiState

import com.example.clinicmanagerfront.data.model.PatientModel
import com.example.clinicmanagerfront.presentation.view.patientsScreen.patientCard.PatientDataCard

data class PatientsUiState(
    val patients: List<PatientModel> = emptyList(),
    val cards: List<PatientDataCard>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)