package com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm.common

data class DropMenuData<T>(
    val items: List<T>,
    val title: String,
    val onItemSelected: (T) -> Unit
)
