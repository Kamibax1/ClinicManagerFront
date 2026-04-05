package com.example.clinicmanagerfront.presentation.view.patientsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.sort.BlockSortCards
import com.example.clinicmanagerfront.presentation.view.patientsScreen.patientCard.PatientCard

@Composable
fun PatientsScreen() {
    val viewModel: PatientsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val verticalScroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(verticalScroll)
    ){
        PatientSearch(
            uiState = uiState,
            onQueryChange = { query -> viewModel.searchPatients(query) }
        )
        Spacer(modifier = Modifier.size(17.5.dp))
        BlockSortCards()
        Spacer(modifier = Modifier.size(32.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            uiState.cards?.forEach { card ->
                PatientCard(card)
            }
        }
    }
}
