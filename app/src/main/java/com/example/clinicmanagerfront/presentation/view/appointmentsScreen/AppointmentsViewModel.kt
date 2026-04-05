package com.example.clinicmanagerfront.presentation.view.appointmentsScreen

import android.icu.text.SimpleDateFormat
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.data.model.AppointmentFullModel
import com.example.clinicmanagerfront.data.model.AppointmentModel
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentDataCard
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.uiState.AppointmentsUiState
import com.example.clinicmanagerfront.ui.theme.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppointmentsUiState())
    val uiState: StateFlow<AppointmentsUiState> = _uiState.asStateFlow()

    init {
        loadAppointments()
    }

    fun loadAppointments() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val appointmentsFull = apiService.getAppointmentsFull()
                val cards = appointmentsFull.map { fullDto ->
                    mapToCard(fullDto)
                }

                _uiState.update { it.copy(cards = cards, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    private fun mapToCard(appointment: AppointmentFullModel): AppointmentDataCard {
        val dateTime = java.time.LocalDateTime.parse(
            appointment.dateTime,
            java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME
        )

        val date = Date.from(dateTime.atZone(java.time.ZoneId.systemDefault()).toInstant())

        val statusText = appointment.status.status.ru
        val doctor = appointment.doctor

        val (iconStatus, iconStatusColor, statusColor) = if (statusText == "Завершено") {
            Triple(Icons.Outlined.CheckCircle, Green700, Green50)
        } else {
            Triple(Icons.Outlined.AccessTime, Blue700, Blue50)
        }

        return AppointmentDataCard(
            date = SimpleDateFormat("d MMMM", Locale.forLanguageTag("ru")).format(date),
            time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(date),
            patient = appointment.patient,
            doctor = doctor,
            doctorSpecialization = doctor.specialization.joinToString(", "),
            status = statusText,
            iconStatus = iconStatus,
            iconStatusColor = iconStatusColor,
            statusColor = statusColor
        )
    }
}
