package com.example.clinicmanagerfront.presentation.view.homeScreen.uiState

import com.example.clinicmanagerfront.data.model.DoctorShortInformationModel
import com.example.clinicmanagerfront.data.model.PatientShortInformationModel

data class HomeFormAppointmentUiState(
    val patients: List<PatientShortInformationModel>? = null,
    val doctors: List<DoctorShortInformationModel>? = null,
    val isLoading: Boolean = false,

    val selectedPatient: PatientShortInformationModel? = null,
    val selectedDoctor: DoctorShortInformationModel? = null,
    val selectedDate: String = "",
    val selectedTime: String = "",
    val symptoms: String = ""
)
