package com.example.clinicmanagerfront.presentation.view.adminPanelScreen.uiEvent

import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.presentation.view.adminPanelScreen.FilterType

sealed class AdminPanelUiEvent {
    data class SearchUser(val partUsername: String) : AdminPanelUiEvent()
    data class UpdateUserUsername(val username: String) : AdminPanelUiEvent()
    data class UpdateUserPassword(val password: String) : AdminPanelUiEvent()
    data class UpdateUserEmail(val email: String) : AdminPanelUiEvent()
    data class UpdateUserEnable(val id: Long) : AdminPanelUiEvent()
    data class UpdateUserRoleById(val id: Long, val role: RoleEnum) : AdminPanelUiEvent()
    data class UpdateUserRole(val role: RoleEnum) : AdminPanelUiEvent()
    data class FilterUsers(val filterType: FilterType) : AdminPanelUiEvent()
    object UpdateSearchActive : AdminPanelUiEvent()
    object UpdateFormActive : AdminPanelUiEvent()
    object UpdateAddUserFormActive : AdminPanelUiEvent()
    object OnConfirm : AdminPanelUiEvent()
}