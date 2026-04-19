package com.example.clinicmanagerfront.presentation.view.homeScreen.uiEvent

import com.example.clinicmanagerfront.data.model.DoctorShortInformationModel
import com.example.clinicmanagerfront.data.model.PatientShortInformationModel

sealed class HomeUiEvent {
    data class ChangeSelectedPatient(val patient: PatientShortInformationModel) : HomeUiEvent()
    data class ChangeSelectedDoctor(val doctor: DoctorShortInformationModel) : HomeUiEvent()
    data class ChangeSelectedDate(val date: String) : HomeUiEvent()
    data class ChangeSelectedTime(val time: String) : HomeUiEvent()
    data class ChangeSymptoms(val symptoms: String) : HomeUiEvent()
    data object OnConfirm : HomeUiEvent()
}
