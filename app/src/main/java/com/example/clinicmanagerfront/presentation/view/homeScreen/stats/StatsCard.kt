package com.example.clinicmanagerfront.presentation.view.homeScreen.stats

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class StatsCard(
    val title: String,
    val count: Int,
    val color: Color,
    val icon: ImageVector
)
