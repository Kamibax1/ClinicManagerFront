package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName

data class DoctorShortInfoResponse(
    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("middle_name")
    val middleName: String,

    @SerializedName("experience_years")
    val experienceYears: Int,

    @SerializedName("specializations")
    val specializations: Set<SpecializationModel>
)
