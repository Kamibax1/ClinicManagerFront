package com.example.clinicmanagerfront.presentation.view.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiState.HomeSearchUiState
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiState.HomeStatsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.String

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _uiStateStats = MutableStateFlow(HomeStatsUiState())
    val uiStateStats: StateFlow<HomeStatsUiState> = _uiStateStats.asStateFlow()

    private val _uiStateSearch = MutableStateFlow(HomeSearchUiState())

    val uiStateSearch: StateFlow<HomeSearchUiState> = _uiStateSearch.asStateFlow()

    init {
        loadStats()
    }

    fun loadStats() {
        viewModelScope.launch {
            try {
                _uiStateStats.value = _uiStateStats.value.copy(isLoading = true)

                val patientsDeferred = async { apiService.getPatients() }
                val completedDeferred = async { apiService.getAppointmentsByStatus() }
                val doctorsDeferred = async { apiService.getDoctors() }
                val appointmentsDeferred = async { apiService.getAppointmentsByDoctorId() }

                _uiStateStats.value = _uiStateStats.value.copy(
                    patientCount = patientsDeferred.await().size,
                    completedCount = completedDeferred.await().size,
                    doctorsCount = doctorsDeferred.await().size,
                    appointmentCount = appointmentsDeferred.await().size,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiStateStats.value = _uiStateStats.value.copy(isLoading = false)
            }
        }
    }

    private var searchJob: Job? = null

    fun searchPatients(partFullName: String) {
        if (partFullName.isBlank()) {
            _uiStateSearch.value = _uiStateSearch.value.copy(patients = emptyList())
            return
        }

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            _uiStateSearch.value = _uiStateSearch.value.copy(isLoading = true)
            Log.d("Search", "Starting search for: '$partFullName'")

            try {
                val patients = apiService.getPatientsByPartFullName(partFullName)
                Log.d("Search", "Patients loaded: ${patients.size} items")
                _uiStateSearch.value = _uiStateSearch.value.copy(
                    patients = patients,
                    isLoading = false
                )
            } catch (e: Exception) {
                Log.e("Search", "Search failed", e)
                _uiStateSearch.value = _uiStateSearch.value.copy(isLoading = false)
            }
        }
    }

}
