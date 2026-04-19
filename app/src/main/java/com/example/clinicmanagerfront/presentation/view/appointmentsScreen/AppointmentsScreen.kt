package com.example.clinicmanagerfront.presentation.view.appointmentsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.navigation.Screen
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.appointmentCard.AppointmentSection
import com.example.clinicmanagerfront.presentation.view.common.sort.BlockSortButtons

@Composable
fun AppointmentsScreen(navController: NavHostController){
    val viewModel: AppointmentsViewModel = hiltViewModel()
    val verticalScroll = rememberScrollState()
    val uiState by viewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.refresh()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 17.5.dp)
            .verticalScroll(verticalScroll),
        verticalArrangement = Arrangement.spacedBy(17.5.dp)
    ){
        Spacer(modifier = Modifier.size(0.dp))
        AppointmentSearch(
            onQueryChange = { query -> viewModel.searchAppointment(query) }
        )
        BlockSortButtons(
            uiState.statusTitles,
            onSortClick = { status -> viewModel.sortAppointment(status) }
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            uiState.groupedCards?.forEach { group ->
                AppointmentSection(
                    group = group,
                    onAppointmentClick = { appointment ->
                        navController.navigate(
                            Screen.AppointmentInformation.createRoute(appointment.id)
                        )
                    }
                )
            }
        }
        Spacer(modifier = Modifier.size(0.dp))
    }
}
