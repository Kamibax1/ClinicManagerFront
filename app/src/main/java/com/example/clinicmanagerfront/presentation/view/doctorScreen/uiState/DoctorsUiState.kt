package com.example.clinicmanagerfront.presentation.view.doctorScreen.uiState

import com.example.clinicmanagerfront.data.model.DoctorModel
import com.example.clinicmanagerfront.presentation.view.doctorScreen.doctorCard.DoctorDataCard

data class DoctorsUiState(
    val doctors: List<DoctorModel> = emptyList(),
    val cards: List<DoctorDataCard>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)