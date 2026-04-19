package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName

data class SpecializationModel(
    @SerializedName("id_specialization")
    val id: Long,

    @SerializedName("name")
    val name: String
)