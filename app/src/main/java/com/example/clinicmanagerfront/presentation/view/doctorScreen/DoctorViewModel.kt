package com.example.clinicmanagerfront.presentation.view.doctorScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.data.model.AppointmentShortInformationModel
import com.example.clinicmanagerfront.data.model.DoctorShortInformationModel
import com.example.clinicmanagerfront.data.model.SpecializationModel
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.presentation.view.doctorScreen.doctorCard.DoctorDataCard
import com.example.clinicmanagerfront.presentation.view.doctorScreen.uiEvent.DoctorUiEvent
import com.example.clinicmanagerfront.presentation.view.doctorScreen.uiState.DoctorsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
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

    fun postUiEvent(event: DoctorUiEvent) {
        when(event) {
            is DoctorUiEvent.SearchDoctor -> {
                _uiState.update { it.copy(selectedSpecializationIndex = -1) }
                searchDoctors(event.name)
            }
            is DoctorUiEvent.SortedDoctors -> {
                _uiState.update { it.copy(selectedSpecializationIndex = event.index) }
                sortDoctors(event.specialization)
            }
        }
    }

    fun loadDoctors() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val specializations = try {
                    apiService.getAllDoctorsSpecializations()
                } catch (e: HttpException) {
                    if (e.code() == 404) emptyList() else throw e
                }
                val doctors = try {
                    apiService.getAllDoctorsShortInfo()
                } catch (e: HttpException) {
                    if (e.code() == 404) emptyList() else throw e
                }
                val cards = doctors.map { doctor ->
                    mapToCard(doctor)
                }
                _uiState.update {
                    it.copy(
                        cards = cards,
                        filteredCards = cards,
                        doctors = doctors,
                        filteredDoctors = doctors,
                        specializations = specializations.map { s -> s.name },
                        isLoading = false
                    ) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun mapToCard(doctor: DoctorShortInformationModel) : DoctorDataCard {
        return DoctorDataCard(
            fullName = "${doctor.lastName} ${doctor.firstName} ${doctor.middleName}",
            experienceYears = doctor.experienceYears,
            specialization = doctor.specializations.joinToString(", ") { it.name }
        )
    }

    fun searchDoctors(partName: String) {
        _uiState.update { it.copy(searchText = partName) }
        searchJob?.cancel()

        if (partName.isBlank()) {
            sortDoctors(specializationName = "Все")
            return
        }

        searchJob = viewModelScope.launch {
            delay(500)
            _uiState.update { it.copy(isLoading = true) }
            try {
                val doctors = try {
                    apiService.getAllDoctorsShortInfoByName(partName)
                } catch (e: HttpException) {
                    if (e.code() == 404) emptyList() else throw e
                }
                val cards = doctors.map { mapToCard(it) }

                _uiState.update {
                    it.copy(
                        filteredCards = cards,
                        filteredDoctors = doctors,
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

            val filteredDoctors = if (specializationName == "Все") _uiState.value.doctors
            else {
                try {
                    apiService.getAllDoctorsShortInfoBySpecialization(specializationName)
                } catch (e: HttpException) {
                    if (e.code() == 404) emptyList() else throw e
                }
            }

            val filteredCards = filteredDoctors.map { mapToCard(it) }

            _uiState.update {
                it.copy(
                    filteredDoctors = filteredDoctors,
                    filteredCards = filteredCards,
                    isLoading = false,
                    error = null
                )
            }
        }
    }
}
