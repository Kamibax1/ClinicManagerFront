package com.example.clinicmanagerfront.presentation.view.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.presentation.view.common.form.*
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun UpdateProfileInformationForm(
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
                text = "Редактировать профиль",
                onDismiss = onDismiss
            )
            Spacer(modifier = Modifier.size(24.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
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
                                value = "Иван",
                                onValueChange = {},
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
                                value = "Петров",
                                onValueChange = {},
                                title = ""
                            )
                        }
                    )
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
                                value = "+7 (916) 123-45-67",
                                onValueChange = {},
                                title = ""
                            )
                        }
                    )
                }
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
