package com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.data.model.DoctorShortInformationModel
import com.example.clinicmanagerfront.data.model.PatientShortInformationModel
import com.example.clinicmanagerfront.presentation.view.common.form.*
import com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm.common.*
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiState.HomeFormAppointmentUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppointmentForm(
    uiState: HomeFormAppointmentUiState,
    onPatientSelected: (PatientShortInformationModel) -> Unit,
    onDoctorSelected: (DoctorShortInformationModel) -> Unit,
    onDataChanged: (String) -> Unit,
    onTimeChanged: (String) -> Unit,
    onSymptomsChanged: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val verticalScroll = rememberScrollState()
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(verticalScroll)
        ) {
            HeaderForm(
                text = "Новая запись",
                onDismiss = onDismiss
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ColField(
                    rowField = {
                        RowField(
                            RowFieldData(
                                icon = Icons.Outlined.PersonOutline,
                                title = "Пациент"
                            )
                        )
                    },
                    composable = {
                        DropMenu(
                            DropMenuData(
                                items = uiState.patients ?: emptyList(),
                                title = "Выберите пациента",
                                onItemSelected = { onPatientSelected(it) }
                            )
                        )
                    }
                )
                ColField(
                    rowField = {
                        RowField(
                            RowFieldData(
                                icon = Icons.Outlined.PersonOutline,
                                title = "Врач"
                            )
                        )
                    },
                    composable = {
                        DropMenu(
                            DropMenuData(
                                items = uiState.doctors ?: emptyList(),
                                title = "Выберите врача",
                                onItemSelected = { onDoctorSelected(it) }
                            )
                        )
                    }
                )
                ColField(
                    rowField = {
                        RowField(
                            RowFieldData(
                                icon = Icons.Outlined.CalendarToday,
                                title = "Дата"
                            )
                        )
                    },
                    composable = {
                        DateField(onValueChange = onDataChanged)
                    }
                )
                ColField(
                    rowField = {
                        RowField(
                            RowFieldData(
                                icon = Icons.Outlined.Schedule,
                                title = "Время"
                            )
                        )
                    },
                    composable = {
                        TimeField(onValueChange = onTimeChanged)
                    }
                )
                ColField(
                    rowField = {
                        RowField(
                            RowFieldData(
                                icon = Icons.Outlined.Sick,
                                title = "Симптомы"
                            )
                        )
                    },
                    composable = {
                        FormTextField(
                            value = uiState.symptoms,
                            onValueChange = onSymptomsChanged,
                            title = "Введите симптомы"
                        )
                    }
                )
                RowButton(onDismiss, onConfirm)
            }
        }
    }
}
