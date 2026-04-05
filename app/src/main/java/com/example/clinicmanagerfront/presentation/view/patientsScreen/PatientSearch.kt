package com.example.clinicmanagerfront.presentation.view.patientsScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.presentation.view.patientsScreen.uiState.PatientsUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientSearch(
    uiState: PatientsUiState,
    onQueryChange: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    DockedSearchBar(
        modifier = Modifier.fillMaxWidth(),
        expanded = active,
        onExpandedChange = { active = it },
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = {
                    query = it
                    onQueryChange(it)
                },
                onSearch = { active = false },
                expanded = active,
                onExpandedChange = { active = it },
                placeholder = {Text(stringResource(id = R.string.search_patient))},
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (active) {
                        IconButton(onClick = { if (query.isNotEmpty()) query = "" else active = false }) {
                            Icon(Icons.Default.Close, contentDescription = "Закрыть")
                        }
                    }
                }
            )
        },
        shape = SearchBarDefaults.dockedShape,
        colors = SearchBarDefaults.colors(),
        tonalElevation = SearchBarDefaults.TonalElevation,
        shadowElevation = 6.dp
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            items(uiState.patients) { patients ->
                ListItem(
                    headlineContent = { Text(patients.fullName) },
                    modifier = Modifier.clickable {
                        query = patients.fullName
                        active = false
                    }
                )
            }
        }
    }
}