package com.example.clinicmanagerfront.presentation.view.homeScreen.fastAction

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun FastActions(
    onOpenForm: () -> Unit,
    onOpenPatients: () -> Unit,
    onOpenDoctors: () -> Unit
){
    val actions = listOf(
        ActionCard(stringResource(id = R.string.appointment), Chart1,ChartBackground1, Icons.Outlined.Add, onOpenForm),
        ActionCard(stringResource(id = R.string.patients), Chart2, ChartBackground2, Icons.Outlined.PersonOutline, onOpenPatients),
        ActionCard(stringResource(id = R.string.doctors), Chart3, ChartBackground3, Icons.Outlined.PersonOutline, onOpenDoctors),
//        ActionCard(stringResource(id = R.string.graph), Chart4, ChartBackground4, Icons.Outlined.InsertChart, {})
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.5.dp)
    ){
        actions.forEach { action ->
            Action(actionCard = action, modifier = Modifier.weight(1f))

        }
    }
}

fun NavController.navigateAndClearBackStack(route: String) {
    navigate(route) {
        popUpTo(graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
