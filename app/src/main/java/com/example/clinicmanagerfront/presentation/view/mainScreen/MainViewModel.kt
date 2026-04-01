package com.example.clinicmanagerfront.presentation.view.mainScreen

import retrofit2.HttpException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.presentation.view.mainScreen.uiState.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.Int

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadAllData()
    }

    fun loadAllData() {
        viewModelScope.launch {
            loadStats()
        }
    }

    fun loadStats() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            viewModelScope.launch {
                val patients = apiService.getPatients()

                _uiState.value = _uiState.value.copy(
                    patientCount = patients.size,
                )
            }
            viewModelScope.launch {
                val completed = apiService.getAppointmentsByStatus()

                _uiState.value = _uiState.value.copy(
                    completedCount = completed.size
                )
            }
            viewModelScope.launch {
                val doctors = apiService.getDoctors()
                _uiState.value = _uiState.value.copy(
                    doctorsCount = doctors.size
                )
            }
            viewModelScope.launch {
                val appointments = apiService.getAppointmentsByDoctorId()

                _uiState.value = _uiState.value.copy(
                    appointmentCount = appointments.size,
                )
            }
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }
}
