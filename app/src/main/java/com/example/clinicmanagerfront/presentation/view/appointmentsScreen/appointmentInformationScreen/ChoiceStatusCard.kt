package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.statusButton.StatusButtonBlock
import com.example.clinicmanagerfront.ui.theme.Card
import com.example.clinicmanagerfront.ui.theme.StatusScheduledContainer
import com.example.clinicmanagerfront.ui.theme.StatusScheduledText

@Composable
fun ChoiceStatusCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Card,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(17.5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Статус"
            )
            Box(
                modifier = Modifier
                    .background(
                        color = StatusScheduledContainer,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(14.dp, 5.25.dp)
            ) {
                Text(
                    text = "Запланировано",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.25.sp,
                        color = StatusScheduledText
                    )
                )
            }
        }
        Spacer(modifier = Modifier.size(14.dp))
        StatusButtonBlock()
    }
}