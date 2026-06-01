package com.example.clinicmanagerfront.presentation.view.authScreens.signUpScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.repository.TokenManager
import com.example.clinicmanagerfront.data.api.AuthService
import com.example.clinicmanagerfront.data.model.RegisterRequest
import com.example.clinicmanagerfront.data.repository.UserRepository
import com.example.clinicmanagerfront.presentation.view.authScreens.signUpScreen.uiEvent.SignUpUiEvent
import com.example.clinicmanagerfront.presentation.view.authScreens.signUpScreen.uiState.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authService: AuthService,
    private val tokenManager: TokenManager,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<SignUpUiState> = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState

    fun postUiEvent(event: SignUpUiEvent) {
        when (event) {
            is SignUpUiEvent.OnEmailChange -> onEmailChange(event.email)
            is SignUpUiEvent.OnUsernameChange -> onUsernameChange(event.username)
            is SignUpUiEvent.OnPasswordChange -> onPasswordChange(event.password)
            is SignUpUiEvent.TogglePasswordVisibility -> togglePasswordVisibility()
            is SignUpUiEvent.OnSignUpClick -> signUp()
            is SignUpUiEvent.ClearError -> clearError()
        }
    }

    private fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }
    private fun onUsernameChange(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    private fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    private fun togglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !_uiState.value.isPasswordVisible) }
    }

    private fun signUp() = viewModelScope.launch {
        val state = _uiState.value
        if (state.email.isBlank() || state.username.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Заполните все поля") }
            return@launch
        }

        // Устанавливаем состояние загрузки
        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null,
                isSuccess = false
            )
        }

        try {
            val usv = _uiState.value
            val response = authService.register(
                RegisterRequest(
                    usv.username,
                    usv.password,
                    usv.email
                )
            )

            // Сохраняем токен
            tokenManager.saveToken(response.token)
            userRepository.fetchUser()

            // Обновляем состояние на успешное
            _uiState.update {
                it.copy(
                    isLoading = false,
                    isSuccess = true
                )
            }
        } catch (e: retrofit2.HttpException) {
            val errorMessage = when (e.code()) {
                401 -> "Неверный логин или пароль"
                403 -> "Доступ запрещен"
                404 -> "Сервер не найден"
                500 -> "Ошибка сервера"
                else -> "Ошибка: ${e.message()}"
            }
            android.util.Log.e("SignInViewModel", "HTTP Error: ${e.code()} - ${e.message()}")
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                errorMessage = errorMessage
            )
        } catch (e: java.io.IOException) {
            android.util.Log.e("SignInViewModel", "Network Error: ${e.message}")
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                errorMessage = "Ошибка сети. Проверьте подключение к интернету"
            )
        } catch (e: Exception) {
            android.util.Log.e("SignInViewModel", "Unexpected Error: ${e.message}", e)
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                errorMessage = "Произошла ошибка: ${e.localizedMessage}"
            )
        }
    }

    private fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}