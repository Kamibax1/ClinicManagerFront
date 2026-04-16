package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.inforamtionCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentInformationScreen.inforamtionCard.rowInfromation.RowInformation
import com.example.clinicmanagerfront.ui.theme.Card

@Composable
fun InformationCard(card: InformationCardData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Card,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(17.5.dp),
        verticalArrangement = Arrangement.spacedBy(10.5.dp)
    ) {
        Text(
            text = card.title
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            card.items.forEach { item ->
                RowInformation(item)
            }
        }
    }
}
