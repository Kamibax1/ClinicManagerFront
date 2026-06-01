package com.example.clinicmanagerfront.presentation.view.profileScreen.uiState

data class ProfileFormUiState(
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val dateOfBirth: String? = null,
    val gender: String? = null,
    val phoneNumber: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
