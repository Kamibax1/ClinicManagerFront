package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName

data class StatusModel (
    @SerializedName("id_status")
    val id: Long,

    @SerializedName("status")
    val status: StatusEnum
)
