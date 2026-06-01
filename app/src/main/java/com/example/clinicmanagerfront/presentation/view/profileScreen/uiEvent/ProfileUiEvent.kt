package com.example.clinicmanagerfront.presentation.view.profileScreen.uiEvent

sealed class ProfileUiEvent {
    data object OnUpdateStatusForm : ProfileUiEvent()
    data object OnUpdateStatusLogoutDialog : ProfileUiEvent()
}