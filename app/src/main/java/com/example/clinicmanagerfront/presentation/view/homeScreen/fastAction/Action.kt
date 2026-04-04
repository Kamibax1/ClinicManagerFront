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
        onClick = { },
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Card
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(14.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
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
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = actionCard.title,
                style = ActionTextStyle
            )
        }
    }
}
