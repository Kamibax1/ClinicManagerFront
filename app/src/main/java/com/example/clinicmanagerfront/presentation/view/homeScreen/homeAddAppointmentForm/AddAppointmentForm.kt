package com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.presentation.view.common.form.*
import com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm.common.*
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiState.HomeFormAppointmentUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppointmentForm(
    uiState: HomeFormAppointmentUiState,
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
                                items = uiState.patients?.map { "${it.lastName} ${it.firstName} ${it.middleName}" } ?: emptyList(),
                                title = "Выберите пациента")
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
                                items = uiState.doctors?.map { "${it.lastName} ${it.firstName} ${it.middleName}" } ?: emptyList(),
                                title = "Выберите врача")
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
                        DateField()
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
                        TimeField()
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
                        FormTextField(title = "Введите симптомы")
                    }
                )
                RowButton(onDismiss, onConfirm)
            }
        }
    }
}
