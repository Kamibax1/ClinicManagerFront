package com.example.clinicmanagerfront.presentation.view.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun Header(title: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Card)
            .padding(start = 14.dp, top = 37.5.dp, end = 14.dp, bottom = 17.5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
            color = BlueText
        )
        Box(
            modifier = Modifier.size(50.dp),
            contentAlignment = Alignment.Center
            ){
            Canvas(modifier = Modifier.matchParentSize()){
                drawCircle(color = Muted)
            }
            Text(
                text = "DA",
                color = BlueText
            )
        }
    }
}