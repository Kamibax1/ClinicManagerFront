package com.example.clinicmanagerfront.presentation.view.doctorScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.data.model.DoctorModel
import com.example.clinicmanagerfront.data.model.PatientModel
import com.example.clinicmanagerfront.presentation.view.doctorScreen.doctorCard.DoctorDataCard
import com.example.clinicmanagerfront.presentation.view.doctorScreen.uiState.DoctorsUiState
import com.example.clinicmanagerfront.presentation.view.patientsScreen.patientCard.PatientDataCard
import com.example.clinicmanagerfront.presentation.view.patientsScreen.uiState.PatientsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class DoctorViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(DoctorsUiState())
    val uiState: StateFlow<DoctorsUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        loadDoctors()
    }

    fun loadDoctors() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val doctors = apiService.getDoctors()
                val cards = doctors.map { doctor ->
                    mapToCard(doctor)
                }

                _uiState.update { it.copy(cards = cards, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun mapToCard(doctor: DoctorModel) : DoctorDataCard {

        return DoctorDataCard(
            firstName = doctor.firstName,
            lastName = doctor.lastName,
            experienceYears = doctor.experienceYears,
            specialization = doctor.specialization.joinToString(", ")
        )
    }

    fun searchDoctors(partName: String) {
        searchJob?.cancel()

        if (partName.isBlank()) {
            loadDoctors()
            return
        }

        searchJob = viewModelScope.launch {
            delay(500)
            _uiState.update { it.copy(isLoading = true) }

            try {
                val doctors = apiService.getDoctorsByPartName(partName)
                val cards = doctors.map { mapToCard(it) }

                _uiState.update {
                    it.copy(
                        cards = cards,
                        doctors = doctors,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}
