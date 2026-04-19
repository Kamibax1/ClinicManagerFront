package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.statusButton

import com.example.clinicmanagerfront.data.model.enums.StatusEnum

data class StatusButtonData(
    val status: StatusEnum,
    val onClick: () -> Unit
)