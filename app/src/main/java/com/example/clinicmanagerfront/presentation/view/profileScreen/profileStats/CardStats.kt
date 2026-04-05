package com.example.clinicmanagerfront.presentation.view.profileScreen.profileStats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.ui.theme.BlueText
import com.example.clinicmanagerfront.ui.theme.Card
import com.example.clinicmanagerfront.ui.theme.Gray600

@Composable
fun CardStats(cardData: CardData, modifier: Modifier) {
    Column(
        modifier = modifier
            .background(
                color = Card,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = cardData.count.toString(),
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 24.sp,
                color = BlueText
            )
        )
        Text(
            text = cardData.title,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.sp,
                color = Gray600
            )
        )
    }
}
