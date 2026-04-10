package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.inforamtionCard.InformationCard
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.inforamtionCard.InformationCardData
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.inforamtionCard.rowInfromation.RowInformationData
import com.example.clinicmanagerfront.ui.theme.BlueText
import com.example.clinicmanagerfront.ui.theme.Card

@Preview
@Composable
fun AppointmentInformationScreen() {
    val verticalScroll = rememberScrollState()

    val appointmentItems = listOf(
        RowInformationData(Icons.Outlined.CalendarToday, "Дата", "Friday, March 27, 2026"),
        RowInformationData(Icons.Outlined.Schedule, "Время", "15:30"),
        RowInformationData(Icons.Outlined.Sick, "Симптомы", "Кашель, повышенная температура, слабость")
    )

    val patientItems = listOf(
        RowInformationData(Icons.Outlined.PersonOutline, "ФИО", ""),
            RowInformationData(Icons.Outlined.Cake, "Дата рождения", ""),
        RowInformationData(Icons.Outlined.Transgender, "Пол", ""),
        RowInformationData(Icons.Outlined.Phone, "Телефон", ""),
        RowInformationData(Icons.Outlined.Email, "Почта", ""),
    )

    val doctorItems = listOf(
        RowInformationData(Icons.Outlined.PersonOutline, "ФИ", ""),
        RowInformationData(Icons.Outlined.BusinessCenter, "Опыт работы", ""),
        RowInformationData(Icons.Outlined.MedicalServices, "Специализации", "")
    )

    val cards = listOf(
        InformationCardData("Запись", appointmentItems),
        InformationCardData("Пациент", patientItems),
        InformationCardData("Доктор", doctorItems)
    )

    Column{
        Spacer(modifier = Modifier.size(1.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Card)
                .padding(17.5.dp),
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 17.5.dp)
                .verticalScroll(verticalScroll),
            verticalArrangement = Arrangement.spacedBy(17.5.dp)
        ) {
            Spacer(modifier = Modifier.size(0.dp))
            ChoiceStatusCard()
            cards.forEach { card ->
                InformationCard(card)
            }
        }
    }
}
