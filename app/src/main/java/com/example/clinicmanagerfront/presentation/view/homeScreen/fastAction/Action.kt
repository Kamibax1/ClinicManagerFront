package com.example.clinicmanagerfront.presentation.view.homeScreen.fastAction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.ui.theme.*
@Composable
fun Action(actionCard: ActionCard, modifier: Modifier){
    Button(
        onClick = actionCard.onClick,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Card),
        shape = RoundedCornerShape(20.dp),
        contentPadding = PaddingValues(14.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(
                        color = actionCard.iconBackgroundColor,
                        shape = RoundedCornerShape(18.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = actionCard.icon,
                    contentDescription = null,
                    tint = actionCard.iconColor,
                    modifier = Modifier.size(21.dp)
                )
            }
            Text(
                text = actionCard.title,
                style = ActionTextStyle
            )
        }
    }
}
