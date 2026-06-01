package com.example.clinicmanagerfront.data.repository

import com.example.clinicmanagerfront.data.api.AuthService
import com.example.clinicmanagerfront.data.model.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val authService: AuthService
) {
    private val _currentUser = MutableStateFlow<UserResponse?>(null)
    val currentUser: StateFlow<UserResponse?> = _currentUser.asStateFlow()

    suspend fun fetchUser() {
        val user = authService.getCurrentUser()
        _currentUser.value = user
    }

    fun updateUser(user: UserResponse) {
        _currentUser.value = user
    }
}