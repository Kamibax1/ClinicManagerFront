package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName

data class PatientShortInformationModel(
    @SerializedName("id_patient_short_information")
    val id: Long,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("middle_name")
    val middleName: String,

    @SerializedName("date_of_birth")
    val dateOfBirth: String,

    @SerializedName("phone_number")
    val phoneNumber: String,
) {
    override fun toString(): String {
        return "$lastName $firstName $middleName"
    }
}