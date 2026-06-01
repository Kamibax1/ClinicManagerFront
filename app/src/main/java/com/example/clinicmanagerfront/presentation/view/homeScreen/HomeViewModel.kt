package com.example.clinicmanagerfront.presentation.view.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.data.api.AuthService
import com.example.clinicmanagerfront.data.model.*
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.data.repository.UserRepository
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiEvent.HomeUiEvent
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiState.HomeFormAppointmentUiState
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiState.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiStateForm = MutableStateFlow(HomeFormAppointmentUiState())
    val uiStateForm: StateFlow<HomeFormAppointmentUiState> = _uiStateForm.asStateFlow()

    init {
        _uiStateForm.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val currentUser: StateFlow<UserResponse?> = userRepository.currentUser
            _uiState.update {
                it.copy(
                    user = currentUser.value,
                    patient = if (currentUser.value?.role == RoleEnum.PATIENT) apiService.getShortInformationPatientByUsername(currentUser.value?.username!!) else null,
                    doctor = if (currentUser.value?.role == RoleEnum.DOCTOR) apiService.getShortInformationDoctorByUsername(currentUser.value?.username!!) else null,
                    textDateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d", Locale.forLanguageTag("ru"))).replaceFirstChar { ch -> ch.uppercase() }
                )
            }
            if (uiState.value.user?.role != RoleEnum.PATIENT) loadStats()
        }
        _uiStateForm.update { it.copy(isLoading = false) }
    }

    fun postUiEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.ChangeSelectedPatient -> onPatientSelected(event.patient)
            is HomeUiEvent.ChangeSelectedDoctor -> onDoctorSelected(event.doctor)
            is HomeUiEvent.ChangeSelectedDate -> onDateChanged(newDate = event.date)
            is HomeUiEvent.ChangeSelectedTime -> onTimeChanged(newTime = event.time)
            is HomeUiEvent.ChangeSymptoms -> onSymptomsChanged(newSymptoms = event.symptoms)
            is HomeUiEvent.OnUpdateStatusForm -> onUpdateStatusForm()
            is HomeUiEvent.OnConfirm -> createAppointment()
        }
    }

    suspend fun loadStats() {
        try {
            _uiState.value = _uiState.value.copy(isLoadingStats = true)

            val stats = apiService.getHomeStats()

            _uiState.value = _uiState.value.copy(
                countAppointmentsToday = stats.countAppointmentToday,
                countPatients = stats.countPatients,
                countDoctors = stats.countDoctors,
                countAppointmentsCompleted = stats.countAppointmentsCompleted,
                isLoadingStats = false
            )
        } catch (e: HttpException) {
            when (e.code()) {
                403 -> {
                    _uiState.update {
                        it.copy(
                            isLoadingStats = false,
                            error = "Access denied"
                        )
                    }
                }

                else -> {
                    _uiState.update {
                        it.copy(
                            isLoadingStats = false,
                            error = "Network error: ${e.message()}"
                        )
                    }
                }
            }
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(isLoadingStats = false)
        }
    }

    fun loadFormInformation() {
        viewModelScope.launch {
            try {
                _uiStateForm.value = _uiStateForm.value.copy(isLoading = true)

                _uiStateForm.value = _uiStateForm.value.copy(
                    patients = if (_uiState.value.user?.role != RoleEnum.PATIENT) apiService.getAllPatientsShortInfo() else null,
                    doctors = if (_uiState.value.user?.role != RoleEnum.DOCTOR) apiService.getAllDoctorsShortInfo() else null,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun onPatientSelected(patient: PatientShortInformationModel?) : PatientShortInformationModel?{
        _uiStateForm.update { it.copy(selectedPatient = patient) }
        return _uiStateForm.value.selectedPatient
    }

    fun onDoctorSelected(doctor: DoctorShortInformationModel?) : DoctorShortInformationModel?{
        _uiStateForm.update { it.copy(selectedDoctor = doctor) }
        return _uiStateForm.value.selectedDoctor
    }

    fun onDateChanged(newDate: String) : String{
        _uiStateForm.update { it.copy(selectedDate = newDate) }
        return _uiStateForm.value.selectedDate
    }

    fun onTimeChanged(newTime: String) : String{
        _uiStateForm.update { it.copy(selectedTime = newTime) }
        return _uiStateForm.value.selectedTime
    }

    fun onSymptomsChanged(newSymptoms: String) : String{
        _uiStateForm.update { it.copy(symptoms = newSymptoms) }
        return _uiStateForm.value.symptoms
    }

    fun onUpdateStatusForm() {
        _uiState.update { it.copy(showModalScreen = !it.showModalScreen) }
    }

    fun createAppointment() {
        val state = _uiStateForm.value
        var patient = state.selectedPatient
        var doctor = state.selectedDoctor
        when(_uiState.value.user?.role){
            RoleEnum.PATIENT -> patient = _uiState.value.patient!!
            RoleEnum.DOCTOR -> doctor = _uiState.value.doctor!!
            else -> {}
        }

        if (patient == null || doctor == null) return

        _uiStateForm.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {

                val appointment = CreateAppointmentModel(
                    date = state.selectedDate,
                    time = state.selectedTime,
                    symptoms = state.symptoms,
                    patientId = patient.id,
                    doctorId = doctor.id
                )
                apiService.saveAppointment(appointment)

                _uiStateForm.update {
                    it.copy(
                        selectedPatient = null,
                        selectedDoctor = null,
                        selectedDate = "",
                        selectedTime = "",
                        symptoms = "",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiStateForm.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }
}
