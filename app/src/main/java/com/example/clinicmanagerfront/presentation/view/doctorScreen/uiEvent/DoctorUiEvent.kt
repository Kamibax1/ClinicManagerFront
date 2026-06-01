package com.example.clinicmanagerfront.presentation.view.doctorScreen.uiEvent

sealed class DoctorUiEvent {
    data class SearchDoctor(val name: String) : DoctorUiEvent()
    data class SortedDoctors(val specialization: String, val index: Int) : DoctorUiEvent()
}