package com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm.common.*
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiState.HomeFormAppointmentUiState
import com.example.clinicmanagerfront.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppointmentForm(
    uiState: HomeFormAppointmentUiState,
    onDismiss: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Новая запись",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        color = BlueText
                    )
                )
                Box(
                    modifier = Modifier
                        .size(32.dp),
//                        .clickable(onClick = onDismiss),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null,
                        tint = Gray500,
                        modifier = Modifier
                            .size(20.dp),
                    )
                }
            }
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
                                items = uiState.patients?.map { it.fullName } ?: emptyList(),
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
                                items = uiState.doctors?.map { "${it.lastName} ${it.firstName}" } ?: emptyList(),
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
                        SymptomsTextField()
                    }
                )
            }
        }
    }
}

@Composable
fun ColField(
    rowField: @Composable () -> Unit,
    composable: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        rowField()
        composable()
    }
}
