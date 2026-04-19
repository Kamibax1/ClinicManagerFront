package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen

import androidx.compose.ui.graphics.vector.ImageVector

data class AppointmentInformationData (
    val date: String,
    val time: String,
    val symptoms: String,

    val patientName: String,
    val patientDateOfBirth: String,
    val patientGenderIcon: ImageVector,
    val patientGender: String,
    val patientPhone: String,
    val patientEmail: String,

    val doctorName: String,
    val doctorExperienceYears: String,
    val doctorPhoneNumber: String,
    val doctorSpecializations: String
)