package com.example.clinicmanagerfront.presentation.view.profileScreen.profileStats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ProfileStats() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CardStats(CardData(28, "Приемов"), Modifier.weight(1f))
        CardStats(CardData(156, "Пациентов"), Modifier.weight(1f))
    }
}
