package com.example.clinicmanagerfront.presentation.view.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun BottomNav() {
    NavigationBar(
        modifier = Modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                bottom = 24.dp
            ),
    ) {

    }
}