package com.example.clinicmanagerfront.presentation.view.authScreens.signUpScreen.uiState

data class SignUpUiState(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)