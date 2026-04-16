package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalTime

data class AppointmentFullInformationResponse(
    @SerializedName("date")
    val date: String,

    @SerializedName("time")
    val time: String,

    @SerializedName("symptoms")
    val symptoms: String,

    @SerializedName("status")
    val status: StatusModel,

    @SerializedName("patient_full_information")
    val patient: PatientFullInformationResponse,

    @SerializedName("doctor_full_information")
    val doctor: DoctorFullInformationResponse
)