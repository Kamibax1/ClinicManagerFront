package com.example.clinicmanagerfront.presentation.view.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.presentation.view.profileScreen.uiState.ProfileUiState
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun PersonalCard(
    uiState: ProfileUiState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp, shape = RoundedCornerShape(12.dp))
            .background(color = MainContentCard)
            .padding(17.5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .background(
                        color = Primary,
                        shape = CircleShape
                    )
                    .size(80.dp),
                contentAlignment = Alignment.Center,
            ){
                Icon(
                    imageVector = Icons.Outlined.PersonOutline,
                    contentDescription = null,
                    tint = Card,
                    modifier = Modifier.size(40.dp)
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = when (uiState.user?.role) {
                        RoleEnum.ADMIN -> uiState.user.username
                        RoleEnum.DOCTOR -> "Доктор ${uiState.doctor?.lastName}"
                        else -> "${uiState.patient?.lastName} ${uiState.patient?.firstName} ${uiState.patient?.middleName}"
                    },
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 18.sp,
                        color = Card
                    )
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = uiState.user?.role?.ru ?: "Пациент",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 14.sp,
                        color = Card
                    )
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Primary,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        ) {
            InfoPerson(Icons.Outlined.Mail, uiState.user?.email ?: "Нет данных")
            Spacer(modifier = Modifier.size(8.dp))
            if(uiState.user?.role != RoleEnum.ADMIN){
                InfoPerson(Icons.Outlined.Phone, if (uiState.patient != null) uiState.patient.phoneNumber
                else if (uiState.doctor != null) uiState.doctor.phoneNumber
                else "Нет данных")
            }
            Spacer(modifier = Modifier.size(8.dp))
            InfoPerson(Icons.Outlined.MedicalServices, "Clinic Manager")
        }
    }
}

@Composable
fun InfoPerson(icon: ImageVector, text: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Card,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 14.sp,
                color = Card
            )
        )
    }
}
