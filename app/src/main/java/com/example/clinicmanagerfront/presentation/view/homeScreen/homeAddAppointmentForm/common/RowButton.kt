package com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm.common

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun RowButton(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ButtonForm(
            ButtonFormData(
                text = "Отмена",
                textColor = Gray700,
                backgroundColor = Gray200
            ),
            Modifier.weight(1f),
            onClick = onDismiss
        )
        ButtonForm(
            ButtonFormData(
                text = "Создать",
                textColor = Card,
                backgroundColor = BlueText
            ),
            Modifier.weight(1f),
            onClick = onConfirm
        )
    }
}