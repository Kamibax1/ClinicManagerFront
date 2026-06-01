package com.example.clinicmanagerfront.data.model

import com.example.clinicmanagerfront.data.model.enums.StatusEnum
import com.google.gson.annotations.SerializedName

data class UpdateAppointmentSymptomsModel(
    @SerializedName("id_doctor")
    val id: Long,

    @SerializedName("symptoms")
    val symptoms: String
)