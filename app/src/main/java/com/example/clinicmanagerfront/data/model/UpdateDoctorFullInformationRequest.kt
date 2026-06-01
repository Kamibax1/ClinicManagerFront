package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName

data class UpdateDoctorFullInformationRequest(
    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("middle_name")
    val middleName: String,

    @SerializedName("phone_number")
    val phoneNumber: String,
)