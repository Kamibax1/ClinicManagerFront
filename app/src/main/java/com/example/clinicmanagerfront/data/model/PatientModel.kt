package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class PatientModel (
    @SerializedName("id_patient")
    val id: Long,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("date_of_birth")
    val dateOfBirth: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("phone_number")
    val phoneNumber: String,

    @SerializedName("email")
    val email: String
)