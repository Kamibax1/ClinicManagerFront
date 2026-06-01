package com.example.clinicmanagerfront.presentation.view.patientsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.data.model.PatientShortInformationModel
import com.example.clinicmanagerfront.presentation.view.patientsScreen.patientCard.PatientDataCard
import com.example.clinicmanagerfront.presentation.view.patientsScreen.uiState.PatientsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.time.LocalDate
import java.time.Period
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class PatientsViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(PatientsUiState())
    val uiState: StateFlow<PatientsUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        loadPatients()
    }

    fun loadPatients() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val patients = try {
                    apiService.getAllPatientsShortInfo()
                } catch (e: HttpException) {
                    if (e.code() == 404) emptyList() else throw e
                }
                val cards = patients.map { patient ->
                    mapToCard(patient)
                }
                _uiState.update { it.copy(cards = cards, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun mapToCard(patient: PatientShortInformationModel) : PatientDataCard {
        val fullName = "${patient.lastName} ${patient.firstName} ${patient.middleName}"
        val age = Period.between(LocalDate.parse(patient.dateOfBirth), LocalDate.now()).years
        return PatientDataCard(
            fullName = fullName,
            age = age,
            phoneNumber = patient.phoneNumber
        )
    }

    fun searchPatients(partName: String) {
        _uiState.update { it.copy(searchText = partName) }
        searchJob?.cancel()

        if (partName.isBlank()) {
            loadPatients()
            return
        }

        searchJob = viewModelScope.launch {
            delay(1000)
            _uiState.update { it.copy(isLoading = true) }

            try {
                val patients = try {
                    apiService.getAllPatientsShortInfoByName(partName)
                } catch (e: HttpException) {
                    if (e.code() == 404) emptyList() else throw e
                }
                val cards = patients.map { mapToCard(it) }

                _uiState.update {
                    it.copy(
                        cards = cards,
                        patients = patients,
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
