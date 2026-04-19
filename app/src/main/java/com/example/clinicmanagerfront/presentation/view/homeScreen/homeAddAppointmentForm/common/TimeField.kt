package com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeField(
    onValueChange: (String) -> Unit
) {
    var message by remember{ mutableStateOf("") }
    var showTimePicker by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState(

    )

    OutlinedTextField(
        value = message,
        onValueChange = { message = it },
        placeholder = { Text(text = "Выберите время") },
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { showTimePicker = true }) {
                Icon(Icons.Default.Schedule, contentDescription = null)
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    )
    if(showTimePicker){
        TimePickerDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val hour = timePickerState.hour.toString().padStart(2, '0')
                    val minute = timePickerState.minute.toString().padStart(2, '0')
                    message = "$hour:$minute"
                    onValueChange(message)
                    showTimePicker = false
                }) { Text("OK") }
            },
            title = { Text("") }
        ) {
            TimePicker(state = timePickerState)
        }
    }
}