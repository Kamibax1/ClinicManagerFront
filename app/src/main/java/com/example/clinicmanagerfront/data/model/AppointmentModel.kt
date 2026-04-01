package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class AppointmentModel (
    @SerializedName("id_appointment")
    val id: Long,

    @SerializedName("date_time")
    val dateTime: LocalDateTime,

    @SerializedName("symptoms")
    val symptoms: String,

    @SerializedName("id_Status")
    val idStatus: Long,

    @SerializedName("id_Patient")
    val idPatient: Long,

    @SerializedName("id_Doctor")
    val idDoctor: Long
)
