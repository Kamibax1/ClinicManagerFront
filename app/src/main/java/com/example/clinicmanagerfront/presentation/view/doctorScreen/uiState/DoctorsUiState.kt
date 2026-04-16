package com.example.clinicmanagerfront.presentation.view.doctorScreen.uiState

import com.example.clinicmanagerfront.data.model.DoctorShortInfoResponse
import com.example.clinicmanagerfront.presentation.view.doctorScreen.doctorCard.DoctorDataCard

data class DoctorsUiState(
    val doctors: List<DoctorShortInfoResponse> = emptyList(),
    val cards: List<DoctorDataCard>? = null,
    val specializations: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)