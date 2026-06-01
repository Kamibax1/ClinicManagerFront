package com.example.clinicmanagerfront.presentation.view.appointmentsScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.presentation.view.appointmentsScreen.uiState.AppointmentsUiState
import com.example.clinicmanagerfront.ui.theme.Card

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentSearch(
    uiState: AppointmentsUiState,
    onQueryChange: (String) -> Unit
) {
    var query by remember { mutableStateOf(uiState.searchText) }
    var active by remember { mutableStateOf(false) }

    DockedSearchBar(
        modifier = Modifier.fillMaxWidth(),
        expanded = false,
        onExpandedChange = { active = it },
        colors = SearchBarDefaults.colors(
            containerColor = Card,
        ),
        inputField = {
            SearchBarDefaults.InputField(
                modifier = Modifier.fillMaxWidth(),
                query = query,
                onQueryChange = {
                    query = it
                    onQueryChange(it)
                },
                onSearch = { active = false },
                expanded = active,
                onExpandedChange = { active = it },
                colors = SearchBarDefaults.inputFieldColors(
                    unfocusedContainerColor = Card,
                    focusedContainerColor = Card
                ),
                placeholder = { Text(stringResource(id = R.string.search_appointment)) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = {
                            query = ""
                            onQueryChange("")
                        }) {
                            Icon(Icons.Default.Close, contentDescription = "Очистить")
                        }
                    }
                }
            )
        },
        shape = SearchBarDefaults.dockedShape,
        content = {}
    )
}
