package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun AppointmentCard(
    appointment: AppointmentDataCard,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .background(
                color = Card,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = BlueTime,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(10.5.dp, 3.5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = appointment.time,
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 12.25.sp,
                            color = BlueText
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            color = appointment.statusColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(10.5.dp, 3.5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = appointment.status,
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 10.5.sp,
                            color = appointment.statusTextColor
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.size(7.dp))
            Text(
                text = appointment.doctorName,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = Gray900
                )
            )
            Spacer(modifier = Modifier.size(3.5.dp))
            Text(
                text = appointment.doctorSpecializations,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 12.25.sp,
                    color = GrayText
                )
            )
        }
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Gray400,
            modifier = Modifier.size(20.dp)
        )
    }
}
