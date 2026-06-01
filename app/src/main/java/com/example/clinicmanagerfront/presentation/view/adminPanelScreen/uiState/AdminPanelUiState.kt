package com.example.clinicmanagerfront.presentation.view.adminPanelScreen.uiState

import com.example.clinicmanagerfront.data.model.UserResponse
import com.example.clinicmanagerfront.presentation.view.adminPanelScreen.userCard.UserDataCard

data class AdminPanelUiState(
    val users: List<UserResponse> = emptyList(),
    val cards: List<UserDataCard> = emptyList(),
    val filteredCards: List<UserDataCard> = emptyList(),
    val searchText: String = "",
    val searchActive: Boolean = false,
    val showFilterMenu: Boolean = false,
    val showAddUserForm: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)