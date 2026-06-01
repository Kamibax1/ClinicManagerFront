package com.example.clinicmanagerfront.presentation.view.profileScreen.profileStats

import androidx.compose.runtime.Composable

data class CardData(
    val count: @Composable () -> Unit,
    val title: String
)