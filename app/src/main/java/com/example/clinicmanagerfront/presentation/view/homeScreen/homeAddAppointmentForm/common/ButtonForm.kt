package com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonForm(button: ButtonFormData, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = button.backgroundColor),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(16.dp, 8.dp),
    ) {
        Text(
            text = button.text,
            style = TextStyle(
                fontSize = 16.sp,
                color = button.textColor
            )
        )
    }
}
