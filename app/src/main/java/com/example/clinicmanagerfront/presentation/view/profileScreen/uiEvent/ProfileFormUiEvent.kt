package com.example.clinicmanagerfront.presentation.view.profileScreen.uiEvent

import java.time.LocalDate

sealed class ProfileFormUiEvent {
    data class OnChangeFirstName(val newFirstName: String) : ProfileFormUiEvent()
    data class OnChangeLastName(val newLastName: String) : ProfileFormUiEvent()
    data class OnChangeMiddleName(val newMiddleName: String) : ProfileFormUiEvent()
    data class OnChangeDateOfBirth(val newDateOfBirth: String) : ProfileFormUiEvent()
    data class OnChangeGender(val newGender: String) : ProfileFormUiEvent()
    data class OnChangePhoneNumber(val newPhoneNumber: String) : ProfileFormUiEvent()
    data object LoadInfo : ProfileFormUiEvent()
    data object UpdateInfo : ProfileFormUiEvent()
}