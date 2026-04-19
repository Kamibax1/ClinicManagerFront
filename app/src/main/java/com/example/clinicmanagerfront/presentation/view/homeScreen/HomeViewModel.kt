package com.example.clinicmanagerfront.presentation.view.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.data.model.*
import com.example.clinicmanagerfront.data.model.enums.StatusEnum
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiEvent.HomeUiEvent
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiState.HomeFormAppointmentUiState
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiState.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiStateForm = MutableStateFlow(HomeFormAppointmentUiState())
    val uiStateForm: StateFlow<HomeFormAppointmentUiState> = _uiStateForm.asStateFlow()

    init {
        loadStats()
    }

    fun postUiEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.ChangeSelectedPatient -> onPatientSelected(event.patient)
            is HomeUiEvent.ChangeSelectedDoctor -> onDoctorSelected(event.doctor)
            is HomeUiEvent.ChangeSelectedDate -> onDateChanged(newDate = event.date)
            is HomeUiEvent.ChangeSelectedTime -> onTimeChanged(newTime = event.time)
            is HomeUiEvent.ChangeSymptoms -> onSymptomsChanged(newSymptoms = event.symptoms)
            is HomeUiEvent.OnConfirm -> createAppointment()
        }
    }

    fun loadStats() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)

                val stats = apiService.getHomeStats()

                _uiState.value = _uiState.value.copy(
                    countAppointmentsToday = stats.countAppointmentToday,
                    countPatients = stats.countPatients,
                    countDoctors = stats.countDoctors,
                    countAppointmentsCompleted = stats.countAppointmentsCompleted,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun loadFormInformation() {
        viewModelScope.launch {
            try {
                _uiStateForm.value = _uiStateForm.value.copy(isLoading = true)

                val patients = apiService.getAllPatientsShortInfo()
                val doctors = apiService.getAllDoctorsShortInfo()

                _uiStateForm.value = _uiStateForm.value.copy(
                    patients = patients,
                    doctors = doctors,
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

    fun createAppointment() {
        val state = _uiStateForm.value

        val patient = state.selectedPatient
        val doctor = state.selectedDoctor


        if (patient == null || doctor == null) return

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
                        symptoms = ""
                    )
                }
            } catch (e: Exception) {
            }
        }
    }
}
