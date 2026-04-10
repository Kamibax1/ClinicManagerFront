package com.example.clinicmanagerfront.presentation.view.doctorScreen.doctorCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PersonOutline
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
fun DoctorCard(doctorCard: DoctorDataCard) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Card,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Person,
                    shape = CircleShape
                )
                .size(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.PersonOutline,
                contentDescription = null,
                tint = Card,
                modifier = Modifier.size(24.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = doctorCard.lastName + " " + doctorCard.firstName,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    color = Gray900
                )
            )
            Row{
                Text(
                    text = "Опыт работы: ",
                    style = InformationCardTextStyle
                )
                Text(
                    text = doctorCard.experienceYears.toString() + " " + getYearsWord(doctorCard.experienceYears),
                    style = InformationCardTextStyle
                )
            }
            Row{
                Text(
                    text = "Специализации: ",
                    style = InformationCardTextStyle
                )
                Text(
                    text = doctorCard.specialization,
                    style = InformationCardTextStyle
                )
            }
        }
    }
}

fun getYearsWord(years: Int): String {
    return when {
        years % 10 == 1 && years % 100 != 11 -> "год"
        years % 10 in 2..4 && years % 100 !in 12..14 -> "года"
        else -> "лет"
    }
}
