package com.example.clinicmanagerfront.presentation.view.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.presentation.view.common.form.*
import com.example.clinicmanagerfront.presentation.view.profileScreen.uiState.ProfileFormUiState
import com.example.clinicmanagerfront.presentation.view.profileScreen.uiState.ProfileUiState
import com.example.clinicmanagerfront.ui.theme.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun UpdateProfileInformationForm(
    uiState: ProfileUiState,
    uiStateForm: ProfileFormUiState,
    onChangeFirstName: (String) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeMiddleName: (String) -> Unit,
    onChangeDateOfBirth: (String) -> Unit,
    onChangeGender: (String) -> Unit,
    onChangePhoneNumber: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
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
                text = "Редактировать профиль",
                onDismiss = onDismiss
            )
            Spacer(modifier = Modifier.size(24.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(96.dp)
                                .background(
                                    color = BlueText,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.PersonOutline,
                                contentDescription = null,
                                tint = Card,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                }

                when {
                    uiStateForm.isLoading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    uiState.error != null -> {
                        item {
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
                    }

                    else -> {
                        item {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                ColField(
                                    rowField = {
                                        RowField(
                                            RowFieldData(
                                                icon = Icons.Outlined.PersonOutline,
                                                title = "Имя"
                                            )
                                        )
                                    },
                                    composable = {
                                        FormTextField(
                                            value = uiStateForm.firstName ?: "",
                                            onValueChange = onChangeFirstName,
                                            title = ""
                                        )
                                    }
                                )
                                ColField(
                                    rowField = {
                                        RowField(
                                            RowFieldData(
                                                icon = Icons.Outlined.PersonOutline,
                                                title = "Фамилия"
                                            )
                                        )
                                    },
                                    composable = {
                                        FormTextField(
                                            value = uiStateForm.lastName ?: "",
                                            onValueChange = onChangeLastName,
                                            title = ""
                                        )
                                    }
                                )
                                ColField(
                                    rowField = {
                                        RowField(
                                            RowFieldData(
                                                icon = Icons.Outlined.PersonOutline,
                                                title = "Отчество"
                                            )
                                        )
                                    },
                                    composable = {
                                        FormTextField(
                                            value = uiStateForm.middleName ?: "",
                                            onValueChange = onChangeMiddleName,
                                            title = ""
                                        )
                                    }
                                )
                                if(uiState.user?.role == RoleEnum.PATIENT){
                                    ColField(
                                        rowField = {
                                            RowField(
                                                RowFieldData(
                                                    icon = Icons.Outlined.PersonOutline,
                                                    title = "Дата рождения"
                                                )
                                            )
                                        },
                                        composable = {
                                            DatePickerField(
                                                uiStateForm,
                                                onValueChange = onChangeDateOfBirth
                                            )
                                        }
                                    )
                                    ColField(
                                        rowField = {
                                            RowField(
                                                RowFieldData(
                                                    icon = if (uiStateForm.gender == "Женский") Icons.Outlined.Female else Icons.Outlined.Male,
                                                    title = "Гендер"
                                                )
                                            )
                                        },
                                        composable = {
                                            FormTextField(
                                                value = uiStateForm.gender ?: "",
                                                onValueChange = onChangeGender,
                                                title = ""
                                            )
                                        }
                                    )
                                }
                                ColField(
                                    rowField = {
                                        RowField(
                                            RowFieldData(
                                                icon = Icons.Outlined.Phone,
                                                title = "Телефон"
                                            )
                                        )
                                    },
                                    composable = {
                                        FormTextField(
                                            value = uiStateForm.phoneNumber ?: "",
                                            onValueChange = onChangePhoneNumber,
                                            title = ""
                                        )
                                    }
                                )
                            }
                        }
                    }
                }

                item {
                    Button(
                        onClick = onConfirm,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = BlueText),
                        contentPadding = PaddingValues(vertical = 12.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Save,
                                contentDescription = null,
                                tint = Card,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Сохранить изменения",
                                style = TextStyle(
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DatePickerField(
    uiStateForm: ProfileFormUiState,
    onValueChange: (String) -> Unit
) {
    var message by remember { mutableStateOf(uiStateForm.dateOfBirth ?: "") }
    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = message,
        onValueChange = { message = it },
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { showDatePicker = true }) {
                Icon(Icons.Default.DateRange, contentDescription = null)
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    )
    if(showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton( onClick = {
                    val date = datePickerState.selectedDateMillis?.let {
                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(it))
                    } ?: ""
                    message = date
                    onValueChange(message)
                    showDatePicker = false
                }) { Text("OK") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}