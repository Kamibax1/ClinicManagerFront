package com.example.clinicmanagerfront.presentation.view.appointmentsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.data.model.AppointmentShortInformationModel
import com.example.clinicmanagerfront.data.model.UserResponse
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.data.model.enums.StatusEnum
import com.example.clinicmanagerfront.data.repository.UserRepository
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentDataCard
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentGroup
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.uiEvent.AppointmentUiEvent
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.uiState.AppointmentsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(
    private val apiService: ApiService,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppointmentsUiState())
    val uiState: StateFlow<AppointmentsUiState> = _uiState.asStateFlow()

    private val dateFormatter = DateTimeFormatter.ofPattern("E, d MMM", Locale.forLanguageTag("ru"))
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    private var searchJob: Job? = null

    fun postUiEvent(event: AppointmentUiEvent) {
        when(event) {
            is AppointmentUiEvent.SearchAppointment -> {
                _uiState.update { it.copy(selectedSpecializationIndex = -1) }
                searchAppointment(event.partDoctorName)
            }
            is AppointmentUiEvent.SortAppointments -> {
                _uiState.update { it.copy(selectedSpecializationIndex = event.index) }
                sortAppointment(event.status)
            }
        }
    }

    init {
        viewModelScope.launch {
            val currentUser: StateFlow<UserResponse?> = userRepository.currentUser
            _uiState.update { it.copy(user = currentUser.value) }
            loadAppointments()
        }
    }

    suspend fun loadAppointments() {
        _uiState.update { it.copy(isLoading = true, error = null) }
        try {
            val statusTitles = apiService.getAllStatus()

            val appointments: List<AppointmentShortInformationModel> = try {
                if (_uiState.value.user?.role != RoleEnum.PATIENT) {
                    apiService.getAllAppointmentsShortInfo()
                } else {
                    val patient = apiService.getShortInformationPatientByUsername(_uiState.value.user?.username ?: "")
                    _uiState.update { it.copy(patientId = patient.id) }
                    apiService.getAllAppointmentsShortInfoByPatientId(patient.id)
                }
            } catch (e: retrofit2.HttpException) {
                if (e.code() == 404) emptyList() else throw e
            }

            val sortedModels = appointments.sortedWith(
                compareBy<AppointmentShortInformationModel> { it.date }
                    .thenBy { it.time }
            )

            val cards = sortedModels.map { mapToCard(it) }
            val groupedCards = groupByDate(cards)

            _uiState.update { it.copy(
                appointments = appointments,
                cards = cards,
                groupedCards = groupedCards,
                statusTitles = statusTitles.map { status -> status.status.ru },
                isLoading = false
            ) }
        } catch (e: Exception) {
            _uiState.update { it.copy(isLoading = false, error = e.message) }
        }
    }

    private fun mapToCard(appointment: AppointmentShortInformationModel): AppointmentDataCard {
        val date = LocalDate.parse(appointment.date)
        val time = LocalTime.parse(appointment.time)

        val formattedDate = date.format(dateFormatter)
            .split(" ")
            .joinToString(" ") { word -> word.replaceFirstChar { it.uppercase() } }

        val formattedTime = time.format(timeFormatter)

        return AppointmentDataCard(
            id = appointment.id,
            date = formattedDate,
            time = formattedTime,
            doctorName = appointment.doctorName,
            symptoms = appointment.symptoms,
            status = appointment.status.status.ru,
            statusColor = appointment.status.status.bgColor,
            statusTextColor = appointment.status.status.textColor
        )
    }

    fun searchAppointment(partDoctorName: String) {
        _uiState.update { it.copy(searchText = partDoctorName) }
        searchJob?.cancel()

        if (partDoctorName.isBlank()) {
            sortAppointment("Все")
            return
        }

        searchJob = viewModelScope.launch {
            delay(500)
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val appointments: List<AppointmentShortInformationModel> = try {
                    if (_uiState.value.user?.role != RoleEnum.PATIENT) {
                        apiService.getAllAppointmentsShortInfoByDoctorName(partDoctorName)
                    } else {
                        apiService.getAllAppointmentsShortInfoByPatientIdAndPartDoctorName(_uiState.value.patientId!!, partDoctorName)
                    }
                } catch (e: retrofit2.HttpException) {
                    if (e.code() == 404) emptyList() else throw e
                }

                val cards = appointments.map { mapToCard(it) }
                val groupedCards = groupByDate(cards)

                _uiState.update {
                    it.copy(
                        cards = cards,
                        groupedCards = groupedCards,
                        filteredAppointments = appointments,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    private fun groupByDate(cards: List<AppointmentDataCard>): List<AppointmentGroup> {
        return cards
            .groupBy { it.date }
            .map { (date, items) -> AppointmentGroup(date, items) }
    }

    fun sortAppointment(status: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val filteredAppointments: List<AppointmentShortInformationModel> = if (status == "Все") {
                    _uiState.value.appointments
                } else {
                    try {
                        if (_uiState.value.user?.role != RoleEnum.PATIENT)
                            apiService.getAllAppointmentsShortInfoByStatus(StatusEnum.fromRu(status)!!)
                        else
                            apiService.getAllAppointmentsShortInfoByPatientIdAndStatus(_uiState.value.patientId!!, StatusEnum.fromRu(status)!!)
                    } catch (e: retrofit2.HttpException) {
                        if (e.code() == 404) emptyList() else throw e
                    }
                }

                val filteredCard = filteredAppointments.map { mapToCard(it) }
                val groupedCards = groupByDate(filteredCard)

                _uiState.update {
                    it.copy(
                        filteredAppointments = filteredAppointments,
                        cards = filteredCard,
                        groupedCards = groupedCards,
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
