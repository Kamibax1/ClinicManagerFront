package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.statusButton

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun StatusButtonBlock(
    onStatusSelected: (String) -> Unit
) {
    val statusButtons = listOf(
        StatusButtonData("Подтверждено", textColor = StatusConfirmedText, backgroundColor = StatusConfirmedContainer, {}),
        StatusButtonData("Завершено", textColor = StatusCompletedText, backgroundColor = StatusCompletedContainer, {}),
        StatusButtonData("Запланировано", textColor = StatusScheduledText, backgroundColor = StatusScheduledContainer, {}),
        StatusButtonData("Отменено", textColor = StatusCancelledText, backgroundColor = StatusCancelledContainer, {})
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(10.5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.5.dp)
        ) {
            StatusButton(
                statusButtons[0].copy(onClick = { onStatusSelected(statusButtons[0].text) }),
                Modifier.weight(1f)
            )
            StatusButton(
                statusButtons[1].copy(onClick = { onStatusSelected(statusButtons[1].text) }),
                Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.5.dp)
        ) {
            StatusButton(
                statusButtons[2].copy(onClick = { onStatusSelected(statusButtons[2].text) }),
                Modifier.weight(1f)
            )
            StatusButton(
                statusButtons[3].copy(onClick = { onStatusSelected(statusButtons[3].text) }),
                Modifier.weight(1f)
            )
        }
    }
}
