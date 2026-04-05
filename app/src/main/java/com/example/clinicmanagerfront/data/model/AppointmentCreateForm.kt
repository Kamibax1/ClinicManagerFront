package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName

data class AppointmentCreateForm(
    @SerializedName("id_appointment")
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
