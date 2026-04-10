package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.statusButton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun StatusButtonBlock() {
    val statusButtons = listOf(
        StatusButtonData("Подтверждено", textColor = StatusConfirmedText, backgroundColor = StatusConfirmedContainer, onClick = {}),
        StatusButtonData("Завершено", textColor = StatusCompletedText, backgroundColor = StatusCompletedContainer, onClick = {}),
        StatusButtonData("Запланировано", textColor = StatusScheduledText, backgroundColor = StatusScheduledContainer, onClick = {}),
        StatusButtonData("Отменено", textColor = StatusCancelledText, backgroundColor = StatusCancelledContainer, onClick = {})
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(10.5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.5.dp)
        ) {
            StatusButton(statusButtons[0], Modifier.weight(1f))
            StatusButton(statusButtons[1], Modifier.weight(1f))
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.5.dp)
        ) {
            StatusButton(statusButtons[2], Modifier.weight(1f))
            StatusButton(statusButtons[3], Modifier.weight(1f))
        }
    }
}