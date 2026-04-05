package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName

data class DoctorModel (
    @SerializedName("id_doctor")
    val id: Long,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("specialization")
    val specialization: List<String>
)