package com.example.clinicmanagerfront.presentation.view.mainScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.presentation.view.common.Header
import com.example.clinicmanagerfront.presentation.view.mainScreen.uiState.MainUiState
import com.example.clinicmanagerfront.ui.theme.*

data class StatsCard(
    val title: String,
    val count: Int,
    val color: Color,
    val icon: ImageVector
)

data class ActionCard(
    val title: String,
    val iconColor: Color,
    val iconBackgroundColor: Color,
    val icon: ImageVector
)

@Composable
fun MainScreen(
) {
    val viewModel: MainViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Column{
        Header()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 17.5.dp)
        ) {
            Spacer(modifier = Modifier.size(17.5.dp))
            SearchPatient()
            WelcomeCard()
            Spacer(modifier = Modifier.size(21.dp))
            BlockStatsCards(uiState)
            Spacer(modifier = Modifier.size(21.dp))
            Text(stringResource(id = R.string.fast_actions))
            Spacer(modifier = Modifier.size(14.dp))
            FastActions()
        }
    }
}

@Composable
fun SearchPatient(){
    var message by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp, shape = RoundedCornerShape(12.dp))
    ){
        TextField(
            value = message,
            onValueChange = { newText -> message = newText},
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text(stringResource(id = R.string.search_patient))},
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Card,
                unfocusedContainerColor = Card
            )
        )
    }
}

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

@Composable
fun BlockStatsCards(
    uiState: MainUiState
){
    val statsCards = listOf(
        StatsCard(stringResource(id = R.string.patients), uiState.patientCount, Chart2, Icons.Outlined.Schedule),
        StatsCard(stringResource(id = R.string.appointment_today), uiState.appointmentCount, Chart1, Icons.Filled.CalendarMonth),
        StatsCard(stringResource(id = R.string.doctors), uiState.doctorsCount, Chart3, Icons.Outlined.Info),
        StatsCard(stringResource(id = R.string.completed), uiState.completedCount, Chart4, Icons.Outlined.CheckCircle)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(21.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(21.dp)
        ) {
            StatsCardItem(
                card = statsCards[0],
                modifier = Modifier.weight(1f)
            )
            StatsCardItem(
                card = statsCards[1],
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(21.dp)
        ) {
            StatsCardItem(
                card = statsCards[2],
                modifier = Modifier.weight(1f)
            )
            StatsCardItem(
                card = statsCards[3],
                modifier = Modifier.weight(1f)
            )
        }
    }
}
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

@Composable
fun FastActions(){
    val actions = listOf(
        ActionCard(stringResource(id = R.string.appointment), Chart1,ChartBackground1, Icons.Outlined.Add),
        ActionCard(stringResource(id = R.string.patient), Chart2, ChartBackground2, Icons.Outlined.PersonOutline),
        ActionCard(stringResource(id = R.string.report), Chart3, ChartBackground3, Icons.Outlined.Description),
        ActionCard(stringResource(id = R.string.graph), Chart4, ChartBackground4, Icons.Outlined.InsertChart
        )
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        actions.forEach { action ->
            Action(actionCard = action, modifier = Modifier.weight(1f))
        }
    }

}

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