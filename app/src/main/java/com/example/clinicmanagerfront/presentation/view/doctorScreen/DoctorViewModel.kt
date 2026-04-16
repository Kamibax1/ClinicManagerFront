package com.example.clinicmanagerfront.presentation.view.doctorScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.data.model.DoctorShortInfoResponse
import com.example.clinicmanagerfront.presentation.view.doctorScreen.doctorCard.DoctorDataCard
import com.example.clinicmanagerfront.presentation.view.doctorScreen.uiState.DoctorsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(DoctorsUiState())
    val uiState: StateFlow<DoctorsUiState> = _uiState.asStateFlow()

    var allDoctors: List<DoctorShortInfoResponse> = emptyList()

    private var searchJob: Job? = null

    init {
        loadDoctors()
    }

    fun loadDoctors() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val specializations = apiService.getAllDoctorsSpecializations()
                val doctors = apiService.getAllDoctorsShortInfo()
                allDoctors = doctors
                val cards = doctors.map { doctor ->
                    mapToCard(doctor)
                }
                _uiState.update {
                    it.copy(
                        cards = cards,
                        doctors = doctors,
                        specializations = specializations.map { s -> s.name },
                        isLoading = false
                    ) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun mapToCard(doctor: DoctorShortInfoResponse) : DoctorDataCard {
        return DoctorDataCard(
            fullName = "${doctor.lastName} ${doctor.firstName} ${doctor.middleName}",
            experienceYears = doctor.experienceYears,
            specialization = doctor.specializations.joinToString(", ") { it.name }
        )
    }

    fun searchDoctors(partName: String) {
        searchJob?.cancel()

        if (partName.isBlank()) {
            sortDoctors("Все")
            return
        }

        searchJob = viewModelScope.launch {
            delay(500)
            _uiState.update { it.copy(isLoading = true) }
            try {
                val doctors = apiService.getAllDoctorsShortInfoByName(partName)
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

    fun sortDoctors(specializationName: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val filteredDoctors = if (specializationName == "Все") allDoctors
            else {
                allDoctors.filter { doctor ->
                    doctor.specializations.map { it.name }.contains(specializationName)
                }
            }

            val filteredCards = filteredDoctors.map { mapToCard(it) }

            _uiState.update {
                it.copy(
                    doctors = filteredDoctors,
                    cards = filteredCards,
                    isLoading = false,
                    error = null
                )
            }
        }
    }
}
