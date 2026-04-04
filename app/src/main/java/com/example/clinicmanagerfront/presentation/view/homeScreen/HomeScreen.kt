package com.example.clinicmanagerfront.presentation.view.homeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.presentation.view.common.BottomNavigationBar
import com.example.clinicmanagerfront.presentation.view.common.Header
import com.example.clinicmanagerfront.presentation.view.homeScreen.fastAction.FastActions
import com.example.clinicmanagerfront.presentation.view.homeScreen.stats.BlockStatsCards

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiStateStats by viewModel.uiStateStats.collectAsState()
    val uiStateSearch by viewModel.uiStateSearch.collectAsState()
    val scrollState = rememberScrollState()

    // Теперь это просто чистый контент экрана
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 17.5.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.size(17.5.dp))
        SearchPatient(
            uiState = uiStateSearch,
            onQueryChange = { query -> viewModel.searchPatients(query) }
        )
        WelcomeCard()
        Spacer(modifier = Modifier.size(21.dp))
        BlockStatsCards(uiStateStats)
        Spacer(modifier = Modifier.size(21.dp))
        Text(stringResource(id = R.string.fast_actions))
        Spacer(modifier = Modifier.size(14.dp))
        FastActions()
    }
}
