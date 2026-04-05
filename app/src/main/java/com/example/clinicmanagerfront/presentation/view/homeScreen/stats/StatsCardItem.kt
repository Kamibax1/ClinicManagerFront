package com.example.clinicmanagerfront.presentation.view.homeScreen.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.ui.theme.*
@Composable
fun StatsCardItem(card: StatsCard, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Card),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(
                        color = card.color,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = card.icon,
                    contentDescription = null,
                    tint = Card,
                    modifier = Modifier.size(21.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.5.dp))

            Text(
                text = card.count.toString(),
                style = StatsTextStyle,
                fontSize = 21.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(3.5.dp))

            Text(
                text = card.title,
                style = StatsTextStyle,
                fontSize = 10.5.sp,
                color = Color.Gray
            )
        }
    }
}
