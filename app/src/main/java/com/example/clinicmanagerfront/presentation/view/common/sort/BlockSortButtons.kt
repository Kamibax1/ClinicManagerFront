package com.example.clinicmanagerfront.presentation.view.common.sort

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BlockSortButtons(
    titles: List<String>,
    selectedIndex: Int,
    onSortClick: (String, Int) -> Unit
) {
    val horizontalScroll = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(horizontalScroll),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        SortButton(
            text = "Все",
            isSelected = -1 == selectedIndex,
            onSelect = {
                onSortClick("Все", -1)
            }
        )
        titles.forEachIndexed { index, title ->
            SortButton(
                text = title,
                isSelected = index == selectedIndex,
                onSelect = {
                    onSortClick(title, index)
                }
            )
        }
    }
}
