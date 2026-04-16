package com.example.clinicmanagerfront.presentation.view.appointmentsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.data.model.AppointmentShortInformationResponse
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentDataCard
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentGroup
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
    private val apiService: ApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppointmentsUiState())
    val uiState: StateFlow<AppointmentsUiState> = _uiState.asStateFlow()

    private val dateFormatter = DateTimeFormatter.ofPattern("E, d MMM", Locale.forLanguageTag("ru"))
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    private var allAppointments: List<AppointmentShortInformationResponse> = emptyList()

    private var searchJob: Job? = null

    init {
        loadAppointments()
    }

    fun loadAppointments() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val statusTitles = apiService.getAllStatus()
                val appointments = apiService.getAllAppointmentsShortInfo()
                allAppointments = appointments
                val sortedModels = appointments.sortedWith(
                    compareBy<AppointmentShortInformationResponse> { it.date }
                        .thenBy { it.time }
                )

                val cards = sortedModels.map { mapToCard(it) }

                val groupedCards = cards.groupBy { it.date }.map { (date, items) ->
                    AppointmentGroup(date, items)
                }

                _uiState.update { it.copy(
                    cards = cards,
                    groupedCards = groupedCards,
                    statusTitles = statusTitles.map { status -> status.status.ru },
                    isLoading = false
                ) }
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    isLoading = false,
                    error = e.message
                ) }
            }
        }
    }

    private fun mapToCard(appointment: AppointmentShortInformationResponse): AppointmentDataCard {
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

    fun searchAppointment(partName: String) {
        searchJob?.cancel()

        if (partName.isBlank()) {
            loadAppointments()
            return
        }

        searchJob = viewModelScope.launch {
            delay(500)
            _uiState.update { it.copy(isLoading = true) }

            try {
                val appointments = apiService.getAllAppointmentsShortInfoByDoctorName(partName)
                val cards = appointments.map { mapToCard(it) }
                val gropedCards = groupByDate(cards)

                _uiState.update {
                    it.copy(
                        cards = cards,
                        groupedCards = gropedCards,
                        appointments = appointments,
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
            _uiState.update { it.copy(isLoading = true) }

            val filteredAppointments = if (status == "Все") allAppointments
            else {
                allAppointments.filter { appointment ->
                    appointment.status.status.ru == status
                }
            }

            val filteredCard = filteredAppointments.map { mapToCard(it) }
            val groupedCards = groupByDate(filteredCard)

            _uiState.update {
                it.copy(
                    appointments = filteredAppointments,
                    cards = filteredCard,
                    groupedCards = groupedCards,
                    isLoading = false,
                    error = null
                )
            }
        }
    }
}
