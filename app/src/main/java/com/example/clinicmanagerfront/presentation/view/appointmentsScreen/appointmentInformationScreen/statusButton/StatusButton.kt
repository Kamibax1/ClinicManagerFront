package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.statusButton

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusButton(button: StatusButtonData, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { button.onClick() }
            .background(color = button.backgroundColor)
            .border(
                color = button.textColor,
                width = 2.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(14.dp, 10.5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = button.text,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.25.sp,
                color = button.textColor
            )
        )
    }
}
