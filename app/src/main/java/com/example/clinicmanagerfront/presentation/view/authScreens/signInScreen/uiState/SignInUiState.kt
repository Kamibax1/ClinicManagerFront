package com.example.clinicmanagerfront.presentation.view.authScreens.signInScreen.uiState

data class SignInUiState(
    val username: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)