package com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun SymptomsTextField() {
    var message by remember{ mutableStateOf("") }

    OutlinedTextField(
        value = message,
        onValueChange = { message = it },
        placeholder = { Text(text = "Введите симптомы") },
        modifier = Modifier.fillMaxWidth()
    )
}
