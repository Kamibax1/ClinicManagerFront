package com.example.clinicmanagerfront.presentation.view.homeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.R
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
    var showModalScreen by remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()
    val uiStateForm by viewModel.uiStateForm.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 17.5.dp)
    ) {
        WelcomeCard()
        Spacer(modifier = Modifier.size(21.dp))
        BlockStatsCards(uiState)
        Spacer(modifier = Modifier.size(21.dp))
        Text(stringResource(id = R.string.fast_actions))
        Spacer(modifier = Modifier.size(14.dp))
        FastActions(
            onOpenForm = {
                viewModel.loadFormInformation()
                showModalScreen = true
            },
            onOpenPatients = { navController.navigateAndClearBackStack(Screen.Patients.route) },
            onOpenDoctors = { navController.navigateAndClearBackStack(Screen.Doctors.route) }
        )

        if (showModalScreen) {
            BasicAlertDialog(
                onDismissRequest = { showModalScreen = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                AddAppointmentForm(
                    uiState = uiStateForm,
                    onDismiss = { showModalScreen = false },
                    onConfirm = {
                        viewModel.postUiEvent(HomeUiEvent.OnConfirm)
                        showModalScreen = false
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
}
