package com.example.clinicmanagerfront.presentation.view.common.sort

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BlockSortButtons(titles: List<String>) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val horizontalScroll = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(horizontalScroll),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        titles.forEachIndexed { index, titles ->
            SortButton(
                text = titles,
                isSelected = index == selectedIndex,
                onSelect = { selectedIndex = index }
            )
        }
    }
}
