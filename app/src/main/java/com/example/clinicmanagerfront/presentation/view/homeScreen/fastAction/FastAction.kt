package com.example.clinicmanagerfront.presentation.view.homeScreen.fastAction

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun FastActions(){
    val actions = listOf(
        ActionCard(stringResource(id = R.string.appointment), Chart1,ChartBackground1, Icons.Outlined.Add),
        ActionCard(stringResource(id = R.string.patient), Chart2, ChartBackground2, Icons.Outlined.PersonOutline),
        ActionCard(stringResource(id = R.string.report), Chart3, ChartBackground3, Icons.Outlined.Description),
        ActionCard(stringResource(id = R.string.graph), Chart4, ChartBackground4, Icons.Outlined.InsertChart
        )
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        actions.forEach { action ->
            Action(actionCard = action, modifier = Modifier.weight(1f))
        }
    }
}
