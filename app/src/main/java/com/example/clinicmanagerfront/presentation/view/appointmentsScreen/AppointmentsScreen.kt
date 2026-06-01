package com.example.clinicmanagerfront.presentation.view.appointmentsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.navigation.Screen
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentSection
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.uiEvent.AppointmentUiEvent
import com.example.clinicmanagerfront.presentation.view.common.EmptyPlaceholder
import com.example.clinicmanagerfront.presentation.view.common.sort.BlockSortButtons

@Composable
fun AppointmentsScreen(
    navController: NavHostController
){
    val viewModel: AppointmentsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading && uiState.groupedCards == null -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.error != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = uiState.error ?: "Unknown error",
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {}) {
                    Text("Retry")
                }
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 17.5.dp),
                verticalArrangement = Arrangement.spacedBy(17.5.dp),
                contentPadding = PaddingValues(vertical = 17.5.dp)
            ) {
                item {
                    AppointmentSearch(
                        uiState = uiState,
                        onQueryChange = { query -> viewModel.postUiEvent(AppointmentUiEvent.SearchAppointment(query)) }
                    )
                }
                item {
                    BlockSortButtons(
                        uiState.statusTitles,
                        selectedIndex = uiState.selectedSpecializationIndex,
                        onSortClick = { status, index -> viewModel.postUiEvent(AppointmentUiEvent.SortAppointments(status, index)) }
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
                        uiState.groupedCards?.let { groups ->
                            if (groups.isEmpty()) {
                                item {
                                    EmptyPlaceholder("Записей не найдено")
                                }
                            } else {
                                items(
                                    count = groups.size,
                                    key = { index -> groups[index].date }
                                ) { index ->
                                    AppointmentSection(
                                        group = groups[index],
                                        onAppointmentClick = { appointment ->
                                            navController.navigate(Screen.AppointmentInformation.createRoute(appointment.id))
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


