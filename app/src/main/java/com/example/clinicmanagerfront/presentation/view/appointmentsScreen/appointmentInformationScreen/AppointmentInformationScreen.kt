package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
import com.example.clinicmanagerfront.ui.theme.Red600

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

    val appointmentInfo = uiState.appointment ?: return

    var showDeleteDialog by remember { mutableStateOf(false) }

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

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Удаление записи") },
            text = { Text("Вы уверены, что хотите удалить эту запись? Это действие нельзя отменить.") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteAppointment(appointmentId)
                        showDeleteDialog = false
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Удалить")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Отмена")
                }
            }
        )
    }

    Column{
        Spacer(modifier = Modifier.size(1.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Card)
                .padding(horizontal = 17.5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clickable {
                        navController.popBackStack()
                    }
                    .padding(vertical =  15.dp),
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
            Button(
                onClick = { showDeleteDialog = true },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red600,
                    contentColor = Card
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Удалить",
                    modifier = Modifier.size(15.5.dp)
                )
                Spacer(modifier = Modifier.size(7.dp))
                Text(
                    fontFamily = FontFamily.SansSerif,
                    text = "Удалить запись",
                    fontSize = 13.sp
                )
            }
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
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}
