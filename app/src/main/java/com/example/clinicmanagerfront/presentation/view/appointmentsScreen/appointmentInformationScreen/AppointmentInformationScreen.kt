package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.data.model.UserResponse
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.data.model.enums.StatusEnum
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.inforamtionCard.InformationCard
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.inforamtionCard.InformationCardData
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.inforamtionCard.rowInfromation.RowInformationData
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.uiEvent.AppointmentInformationUiEvent
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.uiState.AppointmentInformationUiState
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun AppointmentInformationScreen(
    navController: NavHostController,
    viewModel: AppointmentInformationViewModel = hiltViewModel(),
    appointmentId: Long
) {
    val uiState by viewModel.uiState.collectAsState()
    val appointmentInfo = uiState.appointment ?: return
    var showDeleteDialog by remember { mutableStateOf(false) }

    val appointmentItems = listOf(
        RowInformationData(Icons.Outlined.CalendarToday, "Дата", { Text(appointmentInfo.date) }),
        RowInformationData(Icons.Outlined.Schedule, "Время", { Text(appointmentInfo.time) }),
        if (uiState.user?.role != RoleEnum.PATIENT && uiState.accessForDoctor || uiState.user?.role == RoleEnum.ADMIN) {
            RowInformationData(Icons.Outlined.Sick, "Симптомы", { UpdateSymptomsTextField(
                value = uiState.symptoms,
                onValueChange = { viewModel.postUiEvent(AppointmentInformationUiEvent.UpdateSymptoms(it)) }
            ) })
        }
        else
            RowInformationData(Icons.Outlined.Sick, "Симптомы", { Text(appointmentInfo.symptoms) })
    )

    val patientItems = listOf(
        RowInformationData(Icons.Outlined.PersonOutline, "ФИО", { Text(appointmentInfo.patientName) }),
        RowInformationData(Icons.Outlined.Cake, "Дата рождения", { Text(appointmentInfo.patientDateOfBirth) }),
        RowInformationData(appointmentInfo.patientGenderIcon, "Пол", { Text(appointmentInfo.patientGender) }),
        RowInformationData(Icons.Outlined.Phone, "Телефон", { Text(appointmentInfo.patientPhone) }),
        RowInformationData(Icons.Outlined.Email, "Почта", { Text(appointmentInfo.patientEmail) }),
    )

    val doctorItems = listOf(
        RowInformationData(Icons.Outlined.PersonOutline, "ФИО", { Text(appointmentInfo.doctorName) }),
        RowInformationData(Icons.Outlined.BusinessCenter, "Опыт работы", { Text(appointmentInfo.doctorExperienceYears) }),
        RowInformationData(Icons.Outlined.Phone, "Телефон", { Text(appointmentInfo.doctorPhoneNumber) }),
        RowInformationData(Icons.Outlined.MedicalServices, "Специализации", { Text(appointmentInfo.doctorSpecializations) })
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
                    .padding(vertical = 15.dp),
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
            if (uiState.user?.role == RoleEnum.ADMIN) {
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
        }
        when{
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.error != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = uiState.error ?: "Unknown error",
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {}) {
                        Text("Retry")
                    }
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 17.5.dp),
                    verticalArrangement = Arrangement.spacedBy(17.5.dp),
                    contentPadding = PaddingValues(top = 17.5.dp, bottom = 8.dp)
                ) {
                    item {
                        ChoiceStatusCard(
                            onStatusSelected = { status ->
                                val selectedEnum = StatusEnum.entries.find { it.ru == status } ?: StatusEnum.SCHEDULED
                                viewModel.updateAppointmentStatus(selectedEnum)
                            },
                            uiState = uiState
                        )
                    }

                    items(cards.size) { card ->
                        InformationCard(cards[card])
                    }
                }
            }
        }
    }
}

@Composable
fun UpdateSymptomsTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    var message by remember{ mutableStateOf(value) }

    OutlinedTextField(
        value = message,
        onValueChange = {
            message = it
            onValueChange(message)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    )
}
