package com.example.clinicmanagerfront.presentation.view.adminPanelScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.presentation.view.adminPanelScreen.uiState.AdminPanelUiState
import com.example.clinicmanagerfront.ui.theme.Card
import com.example.clinicmanagerfront.ui.theme.Gray900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersSearch(
    uiState: AdminPanelUiState,
    onQueryChange: (String) -> Unit,
    updateSearchActive: () -> Unit,
    onFilterSelected: (FilterType) -> Unit,
    updateFormActive: () -> Unit
) {
    var query by remember { mutableStateOf(uiState.searchText) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DockedSearchBar(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            expanded = false,
            onExpandedChange = { updateSearchActive() },
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
                    onSearch = { updateSearchActive() },
                    expanded = uiState.searchActive,
                    onExpandedChange = { updateSearchActive() },
                    colors = SearchBarDefaults.inputFieldColors(
                        unfocusedContainerColor = Card,
                        focusedContainerColor = Card
                    ),
                    placeholder = { Text(stringResource(id = R.string.search_user)) },
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

        Box {
            IconButton(
                onClick = updateFormActive,
                modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.FilterAlt,
                    contentDescription = "Фильтр",
                    tint = Gray900,
                    modifier = Modifier.fillMaxSize()
                )
            }

            DropdownMenu(
                expanded = uiState.showFilterMenu,
                onDismissRequest = updateFormActive
            ) {
                FilterType.entries.forEach { filterType ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = when(filterType) {
                                    FilterType.ALL -> "Все пользователи"
                                    FilterType.USERNAME -> "По имени пользователя"
                                    FilterType.EMAIL -> "По почте"
                                    FilterType.ACTIVE -> "Активные"
                                    FilterType.BLOCKED -> "Заблокированные"
                                    FilterType.DOCTOR -> "Врачи"
                                    FilterType.PATIENT -> "Пациенты"
                                    FilterType.ADMIN -> "Администраторы"
                                }
                            )
                        },
                        onClick = {
                            updateFormActive()
                            onFilterSelected(filterType)
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = when(filterType) {
                                    FilterType.ALL -> Icons.Default.People
                                    FilterType.USERNAME -> Icons.Default.Person
                                    FilterType.EMAIL -> Icons.Default.Email
                                    FilterType.ACTIVE -> Icons.Default.CheckCircle
                                    FilterType.BLOCKED -> Icons.Default.Block
                                    FilterType.DOCTOR -> Icons.Default.MedicalServices
                                    FilterType.PATIENT -> Icons.Default.Person
                                    FilterType.ADMIN -> Icons.Default.AdminPanelSettings
                                },
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        }
    }
}
