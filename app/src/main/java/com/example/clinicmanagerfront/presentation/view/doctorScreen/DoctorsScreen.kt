package com.example.clinicmanagerfront.presentation.view.doctorScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.clinicmanagerfront.presentation.view.common.EmptyPlaceholder
import com.example.clinicmanagerfront.presentation.view.common.sort.BlockSortButtons
import com.example.clinicmanagerfront.presentation.view.doctorScreen.doctorCard.DoctorCard
import com.example.clinicmanagerfront.presentation.view.doctorScreen.uiEvent.DoctorUiEvent

@Composable
fun DoctorsScreen() {
    val viewModel: DoctorViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.error != null -> {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = uiState.error ?: "Unknown error",
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.loadDoctors() }) {
                        Text("Retry")
                    }
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 17.5.dp),
                    contentPadding = PaddingValues(vertical = 17.5.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        DoctorSearch (
                            uiState = uiState,
                            onQueryChange = { query -> viewModel.postUiEvent(DoctorUiEvent.SearchDoctor(query)) }
                        )
                    }

                    item {
                        BlockSortButtons(
                            titles = uiState.specializations,
                            selectedIndex = uiState.selectedSpecializationIndex,
                            onSortClick = { specialization, index -> viewModel.postUiEvent(DoctorUiEvent.SortedDoctors(specialization, index)) }
                        )
                    }

                    when {
                        uiState.isLoading -> {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillParentMaxWidth()
                                        .fillParentMaxHeight(0.7f)
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                        else -> {
                            uiState.filteredCards?.let { cards ->
                                if (cards.isEmpty()){
                                    item {
                                        EmptyPlaceholder("Врачей не найдено")
                                    }
                                } else {
                                    items(
                                        count = cards.size,
                                        key = { index -> cards[index].fullName}
                                    ) { index ->
                                        DoctorCard(doctorCard = cards[index])
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
