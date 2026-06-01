package com.example.clinicmanagerfront.data.model

import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String
)

data class RegisterRequest(
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("email")
    val email: String,
)

data class AuthResponse(
    @SerializedName("token")
    val token: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("user")
    val user: UserResponse
)

data class UserResponse(
    @SerializedName("id_user")
    val id: Long,

    @SerializedName("username")
    val username: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("enabled")
    val enabled: Boolean,

    @SerializedName("role")
    val role: RoleEnum
)
