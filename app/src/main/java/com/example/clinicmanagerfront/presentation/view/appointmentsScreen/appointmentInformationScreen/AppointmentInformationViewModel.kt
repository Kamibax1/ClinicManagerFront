package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.data.model.*
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.data.model.enums.StatusEnum
import com.example.clinicmanagerfront.data.repository.UserRepository
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.uiEvent.AppointmentInformationUiEvent
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.uiState.AppointmentInformationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AppointmentInformationViewModel @Inject constructor(
    private val apiService: ApiService,
    userRepository: UserRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppointmentInformationUiState())
    val uiState: StateFlow<AppointmentInformationUiState> = _uiState.asStateFlow()

    private var currentAppointmentId: Long? = null

    private var updateJob: Job? = null

    init {
        val currentUser: StateFlow<UserResponse?> = userRepository.currentUser
        _uiState.update { it.copy(user = currentUser.value) }
        savedStateHandle.get<Long>("appointmentId")?.let { appointmentId ->
            currentAppointmentId = appointmentId
            loadAppointmentData(appointmentId)
        }
    }

    fun postUiEvent(event: AppointmentInformationUiEvent) {
        when(event) {
            is AppointmentInformationUiEvent.UpdateStatus -> updateAppointmentStatus(event.status)
            is AppointmentInformationUiEvent.UpdateSymptoms -> updateSymptoms(event.symptoms)
        }
    }

    fun loadAppointmentData(appointmentId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val appointmentInfo = apiService.getAppointmentFullInfoById(appointmentId)
                val appointment = mapToAppointmentData(appointmentInfo)
                if (_uiState.value.user?.role == RoleEnum.DOCTOR){
                    val doctorId = apiService.getShortInformationDoctorByUsername(_uiState.value.user?.username!!).id
                    _uiState.update {
                        it.copy(
                            doctorId = doctorId,
                            accessForDoctor = doctorId == appointmentInfo.doctor.id)
                    }
                }
                _uiState.update {
                    it.copy(
                        appointment = appointment,
                        statusCard = StatusCardData(
                            text = appointmentInfo.status.status,
                            bgColor = appointmentInfo.status.status.bgColor,
                            textColor = appointmentInfo.status.status.textColor
                        ),
                        symptoms = appointment.symptoms,
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
                    if (_uiState.value.user?.role == RoleEnum.DOCTOR) {
                        apiService.updateDoctorAppointmentStatus(
                            id = appointmentId,
                            model = UpdateAppointmentStatusModel(
                                id = _uiState.value.doctorId!!,
                                newStatus
                            )
                        )
                    } else if(_uiState.value.user?.role == RoleEnum.ADMIN) {
                        apiService.updateAppointmentStatus(appointmentId, newStatus)
                    }

                    _uiState.update {
                        it.copy(
                            statusCard = StatusCardData(
                                text = newStatus,
                                textColor = newStatus.textColor,
                                bgColor = newStatus.bgColor
                            ),
                            isLoading = false
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

    fun updateSymptoms(newSymptoms: String){
        updateJob?.cancel()

        currentAppointmentId?.let { appointmentId ->
            updateJob = viewModelScope.launch {
                delay(5000)
                _uiState.update { it.copy(isLoading = true) }
                try {
                    if (_uiState.value.user?.role == RoleEnum.DOCTOR) {
                        apiService.updateDoctorAppointmentSymptoms(
                            id = appointmentId,
                            model = UpdateAppointmentSymptomsModel(
                                id = _uiState.value.doctorId!!,
                                symptoms = newSymptoms
                            )
                        )
                    } else if (_uiState.value.user?.role == RoleEnum.ADMIN) {
                        apiService.updateAppointmentSymptoms(
                            id = appointmentId,
                            symptoms = newSymptoms
                        )
                        _uiState.update {
                            it.copy(
                                symptoms = newSymptoms,
                                isLoading = false
                            )
                        }
                    }
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = "Ошибка обновления статуса: ${e.message}"
                        )
                    }
                }
            }
        }
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
