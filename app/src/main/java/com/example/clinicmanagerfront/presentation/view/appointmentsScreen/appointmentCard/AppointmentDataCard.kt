package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.clinicmanagerfront.data.model.DoctorModel
import com.example.clinicmanagerfront.data.model.PatientModel
import com.example.clinicmanagerfront.data.model.StatusEnum
import com.example.clinicmanagerfront.ui.theme.BlueText
import com.example.clinicmanagerfront.ui.theme.Card
import com.example.clinicmanagerfront.ui.theme.Gray400

data class AppointmentDataCard(
    val date: String,
    val iconDate: ImageVector = Icons.Outlined.CalendarToday,
    val iconDateColor: Color = BlueText,
    val time: String,
    val iconTime: ImageVector = Icons.Outlined.AccessTime,
    val iconTimeColor: Color = Gray400,
    val iconPerson: ImageVector = Icons.Outlined.PersonOutline,
    val iconPersonColor: Color = Card,
    val patient: PatientModel,
    val doctor: DoctorModel,
    val doctorSpecialization: String,
    val status: String,
    val iconStatus: ImageVector,
    val iconStatusColor: Color,
    val statusColor: Color
)
