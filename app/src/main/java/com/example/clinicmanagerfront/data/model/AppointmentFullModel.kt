package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class AppointmentFullModel(
    @SerializedName("id_appointment_full")
    val id: Long,

    @SerializedName("date_time")
    val dateTime: String,

    @SerializedName("symptoms")
    val symptoms: String,

    @SerializedName("status")
    val status: StatusModel,

    @SerializedName("patient")
    val patient: PatientModel,

    @SerializedName("doctor")
    val doctor: DoctorModel
)