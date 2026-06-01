package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName

data class ProfileStatsModel(
    @SerializedName("count_appointment")
    val countAppointment: Int,

    @SerializedName("date_of_registration")
    val dateOfRegistration: String,

    @SerializedName("count_current_appointment")
    val countCurrentAppointment: Int
)