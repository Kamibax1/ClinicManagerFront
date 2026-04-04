package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun AppointmentCard(dataCard: AppointmentDataCard) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Card,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = dataCard.iconDate,
                    contentDescription = null,
                    tint = dataCard.iconDateColor,
                    modifier = Modifier.size(16.dp),
                )

                Text(
                    text = dataCard.date,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = BlueText,
                        fontFamily = FontFamily.SansSerif
                    )
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Icon(
                    imageVector = dataCard.iconTime,
                    contentDescription = null,
                    tint = dataCard.iconTimeColor,
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = dataCard.time,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Gray600,
                        fontFamily = FontFamily.SansSerif
                    )
                )
            }
        }
        Spacer(modifier = Modifier.size(12.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ){
            Box(
                modifier = Modifier.size(40.dp),
                contentAlignment = Alignment.Center
            ){
                Canvas(modifier = Modifier.matchParentSize()){
                    drawCircle(color = Person)
                }
                Icon(
                    imageVector = dataCard.iconPerson,
                    contentDescription = null,
                    tint = dataCard.iconPersonColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = dataCard.patient.fullName,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 18.sp,
                        color = Gray900
                    )
                )
                Text(
                    text = dataCard.patient.phoneNumber,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 14.sp,
                        color = Gray600
                    )
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = "Врач",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 12.sp,
                    color = Gray500
                )
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = dataCard.doctor.lastName + " " + dataCard.doctor.firstName,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = Gray900
                )
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = dataCard.doctorSpecialization,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 12.sp,
                    color = BlueText
                )
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .background(
                        color = dataCard.statusColor,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(12.dp, 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = dataCard.iconStatus,
                    contentDescription = null,
                    tint = dataCard.iconStatusColor,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = dataCard.status,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = dataCard.iconStatusColor,
                        fontFamily = FontFamily.SansSerif
                    )
                )
            }
        }
    }
}
