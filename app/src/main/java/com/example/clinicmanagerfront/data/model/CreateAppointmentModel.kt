package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName

data class CreateAppointmentModel(

    @SerializedName("date")
    val date: String,

    @SerializedName("time")
    val time: String,

    @SerializedName("symptoms")
    val symptoms: String,

    @SerializedName("id_patient_short_information")
    val patientId: Long,

    @SerializedName("id_doctor_short_information")
    val doctorId: Long
)