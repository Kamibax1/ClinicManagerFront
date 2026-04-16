package com.example.clinicmanagerfront.presentation.view.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
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
        loadFormInformation()
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
}
