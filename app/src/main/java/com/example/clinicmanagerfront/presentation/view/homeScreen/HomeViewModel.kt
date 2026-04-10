package com.example.clinicmanagerfront.presentation.view.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiState.HomeFormAppointmentUiState
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiState.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
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
        loadFormInformation()
    }

    fun loadStats() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)

                val patientsDeferred = async { apiService.getPatients() }
                val completedDeferred = async { apiService.getAppointmentsByStatus() }
                val doctorsDeferred = async { apiService.getDoctors() }
                val appointmentsDeferred = async { apiService.getAppointmentsByDoctorId() }

                _uiState.value = _uiState.value.copy(
                    patientCount = patientsDeferred.await().size,
                    completedCount = completedDeferred.await().size,
                    doctorsCount = doctorsDeferred.await().size,
                    appointmentCount = appointmentsDeferred.await().size,
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

                val patients = apiService.getPatients()
                val doctors = apiService.getDoctors()

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
}
