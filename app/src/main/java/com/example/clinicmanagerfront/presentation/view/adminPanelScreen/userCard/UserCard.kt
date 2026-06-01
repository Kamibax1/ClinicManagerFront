package com.example.clinicmanagerfront.presentation.view.adminPanelScreen.userCard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun UserCard(
    user: UserDataCard,
    onUpdateEnabled: () -> Unit,
    onUpdateRole: (RoleEnum) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable {}
            .background(
                color = Card,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = BlueText,
                        shape = CircleShape
                    )
                    .size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.PersonOutline,
                    contentDescription = null,
                    tint = Card,
                    modifier = Modifier.size(24.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = user.username,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 18.sp,
                        color = Gray900
                    )
                )
                Text(
                    text = "Почта: ${user.email}",
                    style = InformationCardTextStyle
                )
                Text(
                    text = "Роль: ${user.role}",
                    style = InformationCardTextStyle
                )
                Text(
                    text = user.enabled,
                    style = InformationCardTextStyle
                )
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = onUpdateEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = user.buttonColor
                )
            ) {
                Text(
                    text = user.buttonText,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.25.sp,
                        color = Card
                    )
                )
            }
            Box {
                Button(
                    onClick = { expanded = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = StatusPendingText
                    )
                ) {
                    Text(
                        text = "Изменить роль",
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 12.25.sp,
                            color = Card
                        )
                    )
                }
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
        }
    }
}
