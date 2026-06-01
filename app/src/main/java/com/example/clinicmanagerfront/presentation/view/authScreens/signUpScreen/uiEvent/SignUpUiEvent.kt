package com.example.clinicmanagerfront.presentation.view.authScreens.signUpScreen.uiEvent

sealed class SignUpUiEvent {
    data class OnEmailChange(val email: String) : SignUpUiEvent()
    data class OnUsernameChange(val username: String) : SignUpUiEvent()
    data class OnPasswordChange(val password: String) : SignUpUiEvent()
    data object TogglePasswordVisibility : SignUpUiEvent()
    data object OnSignUpClick : SignUpUiEvent()
    data object ClearError : SignUpUiEvent()
}