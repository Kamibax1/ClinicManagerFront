package com.example.clinicmanagerfront.presentation.view.adminPanelScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.presentation.view.adminPanelScreen.uiState.CreateUserFormUiState
import com.example.clinicmanagerfront.presentation.view.common.form.*

@Composable
fun CreateUserForm(
    uiState: CreateUserFormUiState,
    onUpdateUsername: (String) -> Unit,
    onUpdatePassword: (String) -> Unit,
    onUpdateEmail: (String) -> Unit,
    onUpdateRole: (RoleEnum) -> Unit,
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
                text = "Новый пользователь",
                onDismiss = {}
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

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            ColField(
                                rowField = {
                                    RowField(
                                        RowFieldData(
                                            icon = Icons.Outlined.Person,
                                            title = "Имя пользователя"
                                        )
                                    )
                                },
                                composable = {
                                    FormTextField(
                                        value = "",
                                        onValueChange = { onUpdateUsername(it) },
                                        title = "Введите имя пользователя"
                                    )
                                }
                            )
                        }

                        item {
                            ColField(
                                rowField = {
                                    RowField(
                                        RowFieldData(
                                            icon = Icons.Outlined.Lock,
                                            title = "Пароль"
                                        )
                                    )
                                },
                                composable = {
                                    FormTextField(
                                        value = "",
                                        onValueChange = { onUpdatePassword(it) },
                                        title = "Введите пароль"
                                    )
                                }
                            )
                        }

                        item {
                            ColField(
                                rowField = {
                                    RowField(
                                        RowFieldData(
                                            icon = Icons.Outlined.Email,
                                            title = "Почта"
                                        )
                                    )
                                },
                                composable = {
                                    FormTextField(
                                        value = "",
                                        onValueChange = { onUpdateEmail(it) },
                                        title = "Введите почту"
                                    )
                                }
                            )
                        }

                        item {
                            ColField(
                                rowField = {
                                    RowField(
                                        RowFieldData(
                                            icon = Icons.Outlined.Badge,
                                            title = "Роль"
                                        )
                                    )
                                },
                                composable = {
                                    RoleField(uiState, onUpdateRole)
                                }
                            )
                        }

                        item {
                            RowButton(onDismiss, onConfirm)
                        }

                        if (uiState.error != null) {
                            item {
                                Text(
                                    text = uiState.error,
                                    color = MaterialTheme.colorScheme.error,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RoleField(
    uiState: CreateUserFormUiState,
    onUpdateRole: (RoleEnum) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf(uiState.role?.name ?: "") }
    OutlinedTextField(
        value = message,
        onValueChange = { message = it },
        placeholder = { Text(text = "Выберите роль") },
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Outlined.Badge, contentDescription = null)
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        RoleEnum.entries.forEach { role ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = when(role) {
                            RoleEnum.PATIENT -> "Пациент"
                            RoleEnum.DOCTOR -> "Врач"
                            RoleEnum.ADMIN -> "Администратор"
                        }
                    )
                },
                onClick = {
                    message = role.ru
                    onUpdateRole(role)
                    expanded = false
                },
                leadingIcon = {
                    Icon(
                        imageVector = when(role) {
                            RoleEnum.PATIENT -> Icons.Filled.Person
                            RoleEnum.DOCTOR -> Icons.Filled.MedicalServices
                            RoleEnum.ADMIN -> Icons.Filled.AdminPanelSettings
                        },
                        contentDescription = null
                    )
                }
            )
        }
    }
}