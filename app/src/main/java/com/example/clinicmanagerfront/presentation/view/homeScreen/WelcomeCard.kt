package com.example.clinicmanagerfront.presentation.view.homeScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun WelcomeCard(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 17.5.dp)
            .shadow(10.dp, shape = RoundedCornerShape(12.dp))
            .clipToBounds()
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MainContentCard,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(21.dp)) {
            Text(
                text = stringResource(id = R.string.welcome),
                style = StatsTextStyle,
                fontSize = 12.25.sp
            )
            Spacer(modifier = Modifier.size(3.5.dp))
            Text(
                text = "Dr. Anderson",
                style = StatsTextStyle,
                fontSize = 15.75.sp
            )
            Spacer(modifier = Modifier.size(14.dp))
            Canvas(modifier = Modifier.fillMaxWidth()) {
                drawCircle(
                    color = Color.White.copy(alpha = 0.2f),
                    radius = 42.dp.toPx(),
                    center = Offset((-22).dp.toPx(), 67.dp.toPx())
                )

                drawCircle(
                    color = Color.White.copy(alpha = 0.3f),
                    radius = 56.dp.toPx(),
                    center = Offset(336.dp.toPx(), (-71).dp.toPx())
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Primary,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(10.5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.today_day),
                    style = StatsTextStyle,
                    fontSize = 10.5.sp
                )
                Text(
                    text = "Friday, March 27",
                    style = StatsTextStyle,
                    fontSize = 12.25.sp
                )
            }
        }
    }
}
