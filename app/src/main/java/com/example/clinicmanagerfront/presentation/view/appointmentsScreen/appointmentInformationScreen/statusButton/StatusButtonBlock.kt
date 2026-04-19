package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.statusButton

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.data.model.enums.StatusEnum

@Composable
fun StatusButtonBlock(
    onStatusSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.5.dp)
    ) {
        StatusEnum.entries.chunked(2).forEach { rowEnums ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.5.dp)
            ) {
                rowEnums.forEach { statusEnum ->
                    StatusButton(
                        StatusButtonData(statusEnum) { onStatusSelected(statusEnum.ru) },
                        Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
