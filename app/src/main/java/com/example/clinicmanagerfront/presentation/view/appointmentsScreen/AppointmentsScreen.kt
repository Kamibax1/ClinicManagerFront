package com.example.clinicmanagerfront.presentation.view.appointmentsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentCard
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.sort.BlockSortCards
@Composable
fun AppointmentsScreen(navController: NavHostController){
    val viewModel: AppointmentsViewModel = hiltViewModel()
    val verticalScroll = rememberScrollState()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 32.dp
            )
            .verticalScroll(verticalScroll)
    ){
        Spacer(modifier = Modifier.size(17.5.dp))
        BlockSortCards()
        Spacer(modifier = Modifier.size(32.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            uiState.cards?.forEach { card ->
                AppointmentCard(card)
            }
        }
    }
}
