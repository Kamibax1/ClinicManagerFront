package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.sort

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BlockSortCards() {
    val cardTitles = listOf("Все", "Сегодня", "Завершенные")
    var selectedIndex by remember { mutableIntStateOf(0) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        cardTitles.forEachIndexed { index, titles ->
            SortCard(
                text = titles,
                isSelected = index == selectedIndex,
                onSelect = { selectedIndex = index }
            )
        }
    }
}
