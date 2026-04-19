package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName

data class AppointmentShortInformationModel(
    @SerializedName("id_appointment_short_info")
    val id: Long,

    @SerializedName("date")
    val date: String,

    @SerializedName("time")
    val time: String,

    @SerializedName("symptoms")
    val symptoms: String,

    @SerializedName("doctor_name")
    val doctorName: String,

    @SerializedName("status")
    val status: StatusModel,
)
