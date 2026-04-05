package com.example.clinicmanagerfront.presentation.view.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.*
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.navigation.Screen
import com.example.clinicmanagerfront.presentation.view.homeScreen.fastAction.FastActions
import com.example.clinicmanagerfront.presentation.view.homeScreen.fastAction.navigateAndClearBackStack
import com.example.clinicmanagerfront.presentation.view.homeScreen.stats.BlockStatsCards
import com.example.clinicmanagerfront.ui.theme.BlueText
import com.example.clinicmanagerfront.ui.theme.Gray500
import com.example.clinicmanagerfront.ui.theme.Gray700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = hiltViewModel()
    var showModalScreen by remember { mutableStateOf(false) }
    val uiStateStats by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 17.5.dp)
    ) {
        WelcomeCard()
        Spacer(modifier = Modifier.size(21.dp))
        BlockStatsCards(uiStateStats)
        Spacer(modifier = Modifier.size(21.dp))
        Text(stringResource(id = R.string.fast_actions))
        Spacer(modifier = Modifier.size(14.dp))
        FastActions(
            onOpenForm = { showModalScreen = true },
            onOpenPatients = { navController.navigateAndClearBackStack(Screen.Patients.route) }
        )

        if (showModalScreen) {
            BasicAlertDialog(
                onDismissRequest = { showModalScreen = false },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                AddAppointmentForm(
                    onDismiss = { showModalScreen = false },
//                    onConfirm = { showModalScreen = false }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppointmentForm(
    onDismiss: () -> Unit
) {
    Surface(

    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Новая запись",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        color = BlueText
                    )
                )
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clickable(onClick = onDismiss),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null,
                        tint = Gray500,
                        modifier = Modifier
                            .size(20.dp),
                    )
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RowField()
            }
        }
    }
}

@Composable
fun RowField() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.PersonOutline,
            contentDescription = null,
            tint = BlueText,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Пациент",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 14.sp,
                color = Gray700
            )
        )
    }
}