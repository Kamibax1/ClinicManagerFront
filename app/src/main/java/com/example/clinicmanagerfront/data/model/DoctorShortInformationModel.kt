package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName

data class DoctorShortInformationModel(
    @SerializedName("id_doctor_short_information")
    val id: Long,

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
) {
    override fun toString(): String {
        return "$lastName $firstName $middleName"
    }
}
