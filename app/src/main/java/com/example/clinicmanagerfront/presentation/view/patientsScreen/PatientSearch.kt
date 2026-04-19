package com.example.clinicmanagerfront.presentation.view.patientsScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.ui.theme.Card

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientSearch(
    onQueryChange: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
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
                placeholder = { Text(stringResource(id = R.string.search_patient)) },
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