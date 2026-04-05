package com.example.clinicmanagerfront.presentation.view.homeScreen.uiState

import com.example.clinicmanagerfront.data.model.DoctorModel
import com.example.clinicmanagerfront.data.model.PatientModel

data class HomeFormAppointmentUiState(
    val patients: List<PatientModel>,
    val doctors: List<DoctorModel>,
)