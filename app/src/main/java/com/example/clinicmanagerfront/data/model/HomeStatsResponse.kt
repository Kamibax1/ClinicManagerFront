package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName

data class HomeStatsResponse(
    @SerializedName("count_appointments_today")
    val countAppointmentToday: Int,

    @SerializedName("count_patients")
    val countPatients: Int,

    @SerializedName("count_doctors")
    val countDoctors: Int,

    @SerializedName("count_appointments_completed")
    val countAppointmentsCompleted: Int
)