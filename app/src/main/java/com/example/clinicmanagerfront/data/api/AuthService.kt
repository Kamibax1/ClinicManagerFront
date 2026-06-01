package com.example.clinicmanagerfront.data.api

import com.example.clinicmanagerfront.data.model.AuthResponse
import com.example.clinicmanagerfront.data.model.LoginRequest
import com.example.clinicmanagerfront.data.model.RegisterRequest
import com.example.clinicmanagerfront.data.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest) : AuthResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest) : AuthResponse

    @GET("auth/me")
    suspend fun getCurrentUser() : UserResponse
}