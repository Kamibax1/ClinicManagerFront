package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.data.model.*
import com.example.clinicmanagerfront.data.model.enums.StatusEnum
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.uiState.AppointmentInformationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AppointmentInformationViewModel @Inject constructor(
    private val apiService: ApiService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppointmentInformationUiState())
    val uiState: StateFlow<AppointmentInformationUiState> = _uiState.asStateFlow()

    private var currentAppointmentId: Long? = null

    init {
        savedStateHandle.get<Long>("appointmentId")?.let { appointmentId ->
            currentAppointmentId = appointmentId
            loadAppointmentData(appointmentId)
        }
    }

    fun loadAppointmentData(appointmentId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val appointmentInfo = apiService.getAppointmentFullInfoById(appointmentId)
                val appointment = mapToAppointmentData(appointmentInfo)
                _uiState.update {
                    it.copy(
                        appointment = appointment,
                        statusCard = StatusCardData(
                            text = appointmentInfo.status.status,
                            bgColor = appointmentInfo.status.status.bgColor,
                            textColor = appointmentInfo.status.status.textColor
                        ),
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun updateAppointmentStatus(newStatus: StatusEnum) {
        currentAppointmentId?.let { appointmentId ->
            viewModelScope.launch {
                _uiState.update { it.copy(isLoading = true) }
                try {
                    apiService.updateStatus(appointmentId, newStatus)

                    _uiState.update {
                        it.copy(
                            statusCard = StatusCardData(
                                text = newStatus,
                                textColor = newStatus.textColor,
                                bgColor = newStatus.bgColor
                            )
                        )
                    }
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(error = "Ошибка обновления статуса: ${e.message}")
                    }
                }
            }
        }
    }

    fun mapToAppointmentData(appointment: AppointmentFullInformationModel) : AppointmentInformationData {
        val doctor = appointment.doctor
        val patient = appointment.patient
        val gender = patient.gender


        return AppointmentInformationData(
            date = LocalDate.parse(appointment.date).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
            time = LocalTime.parse(appointment.time).format(DateTimeFormatter.ofPattern("HH:mm")),
            symptoms = appointment.symptoms,

            patientName = "${patient.lastName} ${patient.firstName} ${patient.middleName}",
            patientDateOfBirth = LocalDate.parse(patient.dateOfBirth).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
            patientGenderIcon = if(gender == "Мужской") Icons.Outlined.Male else if(gender == "Женский") Icons.Outlined.Female else Icons.Outlined.Close,
            patientGender = gender,
            patientPhone = patient.phoneNumber,
            patientEmail = patient.email,

            doctorName = "${doctor.lastName} ${doctor.firstName} ${doctor.middleName}",
            doctorExperienceYears = doctor.experienceYears.toString(),
            doctorPhoneNumber = doctor.phoneNumber,
            doctorSpecializations = doctor.specializations.joinToString(", ") { it.name }
        )
    }

    fun deleteAppointment(appointmentId: Long) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                apiService.deleteAppointmentById(appointmentId)
                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }
}
