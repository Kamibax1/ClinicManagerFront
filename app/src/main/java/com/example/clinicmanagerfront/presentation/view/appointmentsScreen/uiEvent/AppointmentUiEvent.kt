package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.uiEvent

sealed class AppointmentUiEvent {
    data class SearchAppointment(val partDoctorName: String) : AppointmentUiEvent()
    data class SortAppointments(val status: String, val index: Int) : AppointmentUiEvent()
}