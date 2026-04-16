package com.example.clinicmanagerfront.presentation.view.doctorScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.clinicmanagerfront.presentation.view.common.sort.BlockSortButtons
import com.example.clinicmanagerfront.presentation.view.doctorScreen.doctorCard.DoctorCard

@Composable
fun DoctorsScreen() {
    val viewModel: DoctorViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val verticalScroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 17.5.dp)
            .verticalScroll(verticalScroll)
    ){
        Spacer(modifier = Modifier.size(17.5.dp))
        DoctorSearch (
            onQueryChange = { query -> viewModel.searchDoctors(query) }
        )
        Spacer(modifier = Modifier.size(17.5.dp))
        BlockSortButtons(
            titles = uiState.specializations,
            onSortClick = { specialization -> viewModel.sortDoctors(specialization) }
        )
        Spacer(modifier = Modifier.size(17.5.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            uiState.cards?.forEach { card ->
                DoctorCard(card)
            }
        }
    }
}
