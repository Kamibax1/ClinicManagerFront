package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.inforamtionCard.rowInfromation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun RowInformation(info: RowInformationData) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .background(
                    color = BlueTime,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = info.icon,
                contentDescription = null,
                tint = BlueText,
                modifier = Modifier.size(17.5.dp)
            )
        }
        Column{
            Text(
                text = info.title,
                style = TextStyle(
                    fontSize = 12.25.sp,
                    color = GrayText
                )
            )
            Text(
                text = info.text,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Gray900
                )
            )
        }
    }
}
