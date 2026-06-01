package com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.data.model.DoctorShortInformationModel
import com.example.clinicmanagerfront.data.model.PatientShortInformationModel
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.presentation.view.common.form.RowButton
import com.example.clinicmanagerfront.presentation.view.common.form.*
import com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm.common.*
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiState.HomeFormAppointmentUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppointmentForm(
    role: RoleEnum,
    uiState: HomeFormAppointmentUiState,
    onPatientSelected: (PatientShortInformationModel) -> Unit,
    onDoctorSelected: (DoctorShortInformationModel) -> Unit,
    onDataChanged: (String) -> Unit,
    onTimeChanged: (String) -> Unit,
    onSymptomsChanged: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            HeaderForm(
                text = "Новая запись",
                onDismiss = onDismiss
            )
            Spacer(modifier = Modifier.size(16.dp))
            when {
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
                            text = uiState.error,
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
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        if (role != RoleEnum.PATIENT){
                            item {
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
                            }
                        }

                        if (role != RoleEnum.DOCTOR) {
                            item {
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
                            }
                        }

                        item {
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
                        }

                        item {
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
                        }

                        item {
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
                        }

                        item {
                            RowButton(onDismiss, onConfirm)
                        }
                    }
                }
            }
        }
    }
}
