package com.example.clinicmanagerfront.presentation.view.homeScreen.stats

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiState.HomeUiState
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun BlockStatsCards(
    uiState: HomeUiState
){
    val statsCards = listOf(
        StatsCard(
            stringResource(id = R.string.appointment_today),
            { when(uiState.isLoadingStats) {
                true -> {
                    CircularProgressIndicator()
                }
                false -> {
                    Text(
                        text = uiState.countAppointmentsToday.toString(),
                        style = StatsTextStyle,
                        fontSize = 21.sp,
                        color = Gray900
                    )
                }
            } },
            Chart1,
            Icons.Outlined.CalendarMonth),
        StatsCard(
            stringResource(id = R.string.patients),
            { when(uiState.isLoadingStats) {
                true -> {
                    CircularProgressIndicator()
                }
                false -> {
                    Text(
                        text = uiState.countPatients.toString(),
                        style = StatsTextStyle,
                        fontSize = 21.sp,
                        color = Gray900
                    )
                }
            } },
            Chart2, Icons.Outlined.Schedule
        ),
        StatsCard(
            stringResource(id = R.string.doctors),
            { when(uiState.isLoadingStats) {
                true -> {
                    CircularProgressIndicator()
                }
                false -> {
                    Text(
                        text = uiState.countDoctors.toString(),
                        style = StatsTextStyle,
                        fontSize = 21.sp,
                        color = Gray900
                    )
                }
            } },
            Chart3,
            Icons.Outlined.Info
        ),
        StatsCard(
            stringResource(id = R.string.completed),
            { when(uiState.isLoadingStats) {
                true -> {
                    CircularProgressIndicator()
                }
                false -> {
                    Text(
                        text = uiState.countAppointmentsCompleted.toString(),
                        style = StatsTextStyle,
                        fontSize = 21.sp,
                        color = Gray900
                    )
                }
            } },
            Chart4,
            Icons.Outlined.CheckCircle
        )
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
