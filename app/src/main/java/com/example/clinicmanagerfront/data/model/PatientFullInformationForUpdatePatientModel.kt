package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName

data class PatientFullInformationForUpdatePatientModel(
    @SerializedName("id_patient")
    val id: Long,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("middle_name")
    val middleName: String,

    @SerializedName("date_of_birth")
    val dateOfBirth: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("phone_number")
    val phoneNumber: String,
)