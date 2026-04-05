package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class AppointmentModel (
    @SerializedName("id_appointment")
    val id: Long,

    @SerializedName("date_time")
    val dateTime: String,

    @SerializedName("symptoms")
    val symptoms: String,

    @SerializedName("id_status")
    val idStatus: Long,

    @SerializedName("id_patient")
    val idPatient: Long,

    @SerializedName("id_doctor")
    val idDoctor: Long
)
