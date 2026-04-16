package com.example.clinicmanagerfront.presentation.view.profileScreen.uiState

data class ProfileUiState(
    val name: String? = null,
    val role: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val dateRegister: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)