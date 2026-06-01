package com.example.clinicmanagerfront.presentation.view.adminPanelScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.clinicmanagerfront.presentation.view.adminPanelScreen.uiEvent.AdminPanelUiEvent
import com.example.clinicmanagerfront.presentation.view.adminPanelScreen.userCard.UserCard
import com.example.clinicmanagerfront.presentation.view.common.EmptyPlaceholder
import com.example.clinicmanagerfront.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPanelScreen() {
    val viewModel: AdminPanelViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val uiStateForm by viewModel.uiStateForm.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                17.5.dp,
                17.5.dp,
                17.5.dp,
            )
    ) {
        UsersSearch(
            uiState = uiState,
            onQueryChange = { viewModel.postUiEvent(AdminPanelUiEvent.SearchUser(it)) },
            updateSearchActive = { viewModel.postUiEvent(AdminPanelUiEvent.UpdateSearchActive) },
            onFilterSelected = { viewModel.postUiEvent(AdminPanelUiEvent.FilterUsers(it)) },
            updateFormActive = { viewModel.postUiEvent(AdminPanelUiEvent.UpdateFormActive) }
        )
        Spacer(modifier = Modifier.size(17.5.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            uiState.filteredCards.let { cards ->
                if (cards.isEmpty()){
                    item {
                        EmptyPlaceholder("Пользователей не найдено")
                    }
                } else {
                    items(
                        count = cards.size,
                        key = { index -> cards[index].username}
                    ) { index ->
                        UserCard(
                            user = cards[index],
                            onUpdateEnabled = { viewModel.postUiEvent(AdminPanelUiEvent.UpdateUserEnable(cards[index].id)) },
                            onUpdateRole = { viewModel.postUiEvent(AdminPanelUiEvent.UpdateUserRoleById(id = cards[index].id, role = it)) }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.size(5.dp))
        Button(
            onClick = { viewModel.postUiEvent(AdminPanelUiEvent.UpdateAddUserFormActive) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green700
            )
        ) {
            Text(
                text = "Создать нового пользователя"
            )
        }
    }

    if (uiState.showAddUserForm) {
        BasicAlertDialog(
            onDismissRequest = { viewModel.postUiEvent(AdminPanelUiEvent.UpdateAddUserFormActive) },
            modifier = Modifier.fillMaxWidth()
        ) {
            CreateUserForm(
                uiState = uiStateForm,
                onUpdateUsername = { viewModel.postUiEvent(AdminPanelUiEvent.UpdateUserUsername(it)) },
                onUpdatePassword = { viewModel.postUiEvent(AdminPanelUiEvent.UpdateUserPassword(it)) },
                onUpdateEmail = { viewModel.postUiEvent(AdminPanelUiEvent.UpdateUserEmail(it)) },
                onUpdateRole = { viewModel.postUiEvent(AdminPanelUiEvent.UpdateUserRole(it)) },
                onDismiss = { viewModel.postUiEvent(AdminPanelUiEvent.UpdateAddUserFormActive) },
                onConfirm = { viewModel.postUiEvent(AdminPanelUiEvent.OnConfirm) }
            )
        }
    }
}