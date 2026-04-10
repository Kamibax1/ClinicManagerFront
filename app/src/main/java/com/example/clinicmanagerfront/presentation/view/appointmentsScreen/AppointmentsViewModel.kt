package com.example.clinicmanagerfront.presentation.view.appointmentsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.data.model.AppointmentFullModel
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentDataCard
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentGroup
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.uiState.AppointmentsUiState
import com.example.clinicmanagerfront.ui.theme.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
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

    private var searchJob: Job? = null

    init {
        loadAppointments()
    }

    fun loadAppointments() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val appointmentsFull = apiService.getAppointmentsFull()

                val sortedModels = appointmentsFull.sortedBy { LocalDateTime.parse(it.dateTime) }

                val cards = sortedModels.map { mapToCard(it) }

                val groupedCards = cards.groupBy { it.date }.map { (date, items) ->
                    AppointmentGroup(date, items)
                }

                _uiState.update { it.copy(
                    cards = cards,
                    groupedCards = groupedCards,
                    isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    isLoading = false,
                    error = e.message) }
            }
        }
    }

    private fun mapToCard(appointment: AppointmentFullModel): AppointmentDataCard {
        val dateTime = LocalDateTime.parse(appointment.dateTime)

        val formattedDate = dateTime.format(dateFormatter)
            .split(" ")
            .joinToString(" ") { word -> word.replaceFirstChar { it.uppercase() } }

        val formattedTime = dateTime.format(timeFormatter)

        val statusText = appointment.status.status.ru
        val (statusColor, statusTextColor) = when (statusText) {
            "Запланировано" -> Pair(StatusScheduledContainer, StatusScheduledText)
            "Завершено" -> Pair(StatusCompletedContainer, StatusCompletedText)
            "Подтверждено" -> Pair(StatusConfirmedContainer, StatusConfirmedText)
            "Отменено" -> Pair(StatusCancelledContainer, StatusCancelledText)
            else -> Pair(Blue50, BlueText)
        }

        return AppointmentDataCard(
            date = formattedDate,
            time = formattedTime,
            doctorName = "${appointment.doctor.lastName} ${appointment.doctor.firstName}",
            doctorSpecializations = appointment.doctor.specialization.joinToString(", "),
            status = statusText,
            statusColor = statusColor,
            statusTextColor = statusTextColor
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
                val appointments = apiService.getAppointmentsByPartDoctorName(partName)
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
}
