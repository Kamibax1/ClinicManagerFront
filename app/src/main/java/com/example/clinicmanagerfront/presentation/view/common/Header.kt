package com.example.clinicmanagerfront.presentation.view.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun Header(title: String, icon: ImageVector, backArrow: Boolean, navController: NavHostController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Card)
            .padding(start = 14.dp, top = 30.dp, end = 14.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (backArrow) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
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
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BlueText
            )
        }
    }
}