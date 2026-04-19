package com.example.clinicmanagerfront.presentation.view.common.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    title: String
) {
    var message by remember{ mutableStateOf(value) }

    OutlinedTextField(
        value = message,
        onValueChange = {
            message = it
            onValueChange(message)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        placeholder = { Text(text = title) }
    )
}
