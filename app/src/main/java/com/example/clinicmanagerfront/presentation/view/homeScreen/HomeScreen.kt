package com.example.clinicmanagerfront.presentation.view.homeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.navigation.Screen
import com.example.clinicmanagerfront.presentation.view.homeScreen.fastAction.FastActions
import com.example.clinicmanagerfront.presentation.view.homeScreen.fastAction.navigateAndClearBackStack
import com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm.AddAppointmentForm
import com.example.clinicmanagerfront.presentation.view.homeScreen.stats.BlockStatsCards
import com.example.clinicmanagerfront.presentation.view.homeScreen.uiEvent.HomeUiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val uiStateForm by viewModel.uiStateForm.collectAsState()
    val role = uiState.user?.role

    when {
        uiState.isLoading -> {
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
                    .padding(horizontal = 17.5.dp)
            ) {
                item {
                    WelcomeCard(uiState)
                    Spacer(modifier = Modifier.size(21.dp))
                }
                if(role != RoleEnum.PATIENT && role != null) {
                    item {
                        BlockStatsCards(uiState)
                        Spacer(modifier = Modifier.size(21.dp))
                    }
                }

                item {
                    Text(stringResource(id = R.string.fast_actions))
                    Spacer(modifier = Modifier.size(14.dp))
                    FastActions(
                        role = role ?: RoleEnum.PATIENT,
                        onOpenForm = {
                            viewModel.loadFormInformation()
                            viewModel.postUiEvent(HomeUiEvent.OnUpdateStatusForm)
                        },
                        onOpenPatients = { navController.navigateAndClearBackStack(Screen.Patients.route) },
                        onOpenDoctors = { navController.navigateAndClearBackStack(Screen.Doctors.route) }
                    )
                }
            }
        }
    }

    if (uiState.showModalScreen) {
        BasicAlertDialog(
            onDismissRequest = { viewModel.postUiEvent(HomeUiEvent.OnUpdateStatusForm) },
            modifier = Modifier.fillMaxWidth()
        ) {
            AddAppointmentForm(
                role = role ?: RoleEnum.PATIENT,
                uiState = uiStateForm,
                onDismiss = { viewModel.postUiEvent(HomeUiEvent.OnUpdateStatusForm) },
                onConfirm = {
                    viewModel.postUiEvent(HomeUiEvent.OnConfirm)
                    viewModel.postUiEvent(HomeUiEvent.OnUpdateStatusForm)
                },
                onPatientSelected = { viewModel.postUiEvent(HomeUiEvent.ChangeSelectedPatient(it)) },
                onDoctorSelected = { viewModel.postUiEvent(HomeUiEvent.ChangeSelectedDoctor(it)) },
                onDataChanged = { viewModel.postUiEvent(HomeUiEvent.ChangeSelectedDate(it)) },
                onTimeChanged = { viewModel.postUiEvent(HomeUiEvent.ChangeSelectedTime(it)) },
                onSymptomsChanged = { viewModel.postUiEvent(HomeUiEvent.ChangeSymptoms(it)) }
            )
        }
    }
}
