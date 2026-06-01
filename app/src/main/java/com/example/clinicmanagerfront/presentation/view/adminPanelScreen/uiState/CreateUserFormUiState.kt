package com.example.clinicmanagerfront.presentation.view.adminPanelScreen.uiState

import com.example.clinicmanagerfront.data.model.enums.RoleEnum

data class CreateUserFormUiState(
    val username: String? = null,
    val password: String? = null,
    val email: String? = null,
    val enabled: Boolean? = null,
    val role: RoleEnum? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)