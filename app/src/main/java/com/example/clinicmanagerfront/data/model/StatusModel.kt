package com.example.clinicmanagerfront.data.model

import com.example.clinicmanagerfront.data.model.enums.StatusEnum
import com.google.gson.annotations.SerializedName

data class StatusModel (
    @SerializedName("id_status")
    val id: Long,

    @SerializedName("status")
    val status: StatusEnum
)
