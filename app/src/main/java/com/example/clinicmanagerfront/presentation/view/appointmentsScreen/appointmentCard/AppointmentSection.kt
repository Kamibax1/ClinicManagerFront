package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.ui.theme.GrayText

@Composable
fun AppointmentSection(
    group: AppointmentGroup,
    onAppointmentClick: (AppointmentDataCard) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = group.date,
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.25.sp,
            color = GrayText,
            modifier = Modifier.padding(bottom = 10.5.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(10.5.dp)
        ) {
            group.appointments.forEach { appointment ->
                AppointmentCard(
                    appointment = appointment,
                    onClick = { onAppointmentClick(appointment) }
                )
            }
        }
    }
}
