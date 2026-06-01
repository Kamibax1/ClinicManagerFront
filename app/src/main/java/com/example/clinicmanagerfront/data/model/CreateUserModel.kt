package com.example.clinicmanagerfront.data.model

import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.google.gson.annotations.SerializedName

data class CreateUserModel(
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("enabled")
    val enabled: Boolean,

    @SerializedName("role")
    val role: RoleEnum
)
