package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.data.model.enums.StatusEnum
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.inforamtionCard.InformationCard
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.inforamtionCard.InformationCardData
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.inforamtionCard.rowInfromation.RowInformationData
import com.example.clinicmanagerfront.ui.theme.BlueText
import com.example.clinicmanagerfront.ui.theme.Card

@Composable
fun AppointmentInformationScreen(
    navController: NavHostController,
    viewModel: AppointmentInformationViewModel = hiltViewModel(),
    appointmentId: Long
) {

    LaunchedEffect(appointmentId) {
        viewModel.loadAppointmentData(appointmentId)
    }

    val uiState by viewModel.uiState.collectAsState()
    val verticalScroll = rememberScrollState()

    if (uiState.isLoading && uiState.appointment == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    if (uiState.error != null && uiState.appointment == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Ошибка загрузки: ${uiState.error}")
                Button(onClick = { viewModel.loadAppointmentData(appointmentId) }) {
                    Text("Повторить")
                }
            }
        }
        return
    }

    val appointmentInfo = uiState.appointment ?: return

    val appointmentItems = listOf(
        RowInformationData(Icons.Outlined.CalendarToday, "Дата", appointmentInfo.date),
        RowInformationData(Icons.Outlined.Schedule, "Время", appointmentInfo.time),
        RowInformationData(Icons.Outlined.Sick, "Симптомы", appointmentInfo.symptoms)
    )

    val patientItems = listOf(
        RowInformationData(Icons.Outlined.PersonOutline, "ФИО", appointmentInfo.patientName),
        RowInformationData(Icons.Outlined.Cake, "Дата рождения", appointmentInfo.patientDateOfBirth),
        RowInformationData(appointmentInfo.patientGenderIcon, "Пол", appointmentInfo.patientGender),
        RowInformationData(Icons.Outlined.Phone, "Телефон", appointmentInfo.patientPhone),
        RowInformationData(Icons.Outlined.Email, "Почта", appointmentInfo.patientEmail),
    )

    val doctorItems = listOf(
        RowInformationData(Icons.Outlined.PersonOutline, "ФИО", appointmentInfo.doctorName),
        RowInformationData(Icons.Outlined.BusinessCenter, "Опыт работы", appointmentInfo.doctorExperienceYears),
        RowInformationData(Icons.Outlined.Phone, "Телефон", appointmentInfo.doctorPhoneNumber),
        RowInformationData(Icons.Outlined.MedicalServices, "Специализации", appointmentInfo.doctorSpecializations)
    )

    val cards = listOf(
        InformationCardData("Запись", appointmentItems),
        InformationCardData("Пациент", patientItems),
        InformationCardData("Доктор", doctorItems)
    )

    Column{
        Spacer(modifier = Modifier.size(1.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.popBackStack()

                }
                .background(color = Card)
                .padding(17.5.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = BlueText,
                modifier = Modifier.size(17.5.dp)
            )
            Text(
                text = "Назад",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = BlueText
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 17.5.dp)
                .verticalScroll(verticalScroll),
            verticalArrangement = Arrangement.spacedBy(17.5.dp)
        ) {
            Spacer(modifier = Modifier.size(0.dp))
            ChoiceStatusCard(
                onStatusSelected = { status ->
                    val selectedEnum = StatusEnum.entries.find { it.ru == status } ?: StatusEnum.SCHEDULED
                    viewModel.updateAppointmentStatus(selectedEnum)
                },
                uiState = uiState
            )
            cards.forEach { card ->
                InformationCard(card)
            }
            Spacer(modifier = Modifier.size(0.dp))
        }
    }
}
