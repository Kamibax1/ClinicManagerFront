package com.example.clinicmanagerfront.presentation.view.profileScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.navigation.Screen
import com.example.clinicmanagerfront.presentation.view.profileScreen.profileButtons.ProfileButtons
import com.example.clinicmanagerfront.presentation.view.profileScreen.profileStats.ProfileStats
import com.example.clinicmanagerfront.presentation.view.profileScreen.uiEvent.ProfileFormUiEvent
import com.example.clinicmanagerfront.presentation.view.profileScreen.uiEvent.ProfileUiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {

    val viewModel: ProfileViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val uiStateForm by viewModel.uiStateForm.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.logoutEvent.collect {
            navController.navigate(Screen.SignIn.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    if (uiState.showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.postUiEvent(ProfileUiEvent.OnUpdateStatusLogoutDialog) },
            title = {
                Text("Выход из аккаунта")
            },
            text = {
                Text("Вы уверены, что хотите выйти?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.postUiEvent(ProfileUiEvent.OnUpdateStatusLogoutDialog)
                        viewModel.logout()
                    }
                ) {
                    Text("Выйти", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.postUiEvent(ProfileUiEvent.OnUpdateStatusLogoutDialog) }) {
                    Text("Отмена")
                }
            }
        )
    }

    when {
        uiState.isLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.error != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = uiState.error ?: "Unknown error",
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {}) {
                    Text("Retry")
                }
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(17.5.dp),
                contentPadding = PaddingValues(17.5.dp)
            ) {
                item {
                    PersonalCard(
                        uiState = uiState
                    )
                }

                if (uiState.user?.role != RoleEnum.ADMIN) {
                    item {
                        ProfileStats(uiState = uiState)
                    }
                }

                item {
                    ProfileButtons(
                        onOpenForm = { viewModel.postUiEvent(ProfileUiEvent.OnUpdateStatusForm) },
                        onLogout = { viewModel.postUiEvent(ProfileUiEvent.OnUpdateStatusLogoutDialog) },
                        onOpenScreen = { navController.navigate(Screen.AdminPanel.route) },
                        uiState = uiState
                    )
                }
            }

            if (uiState.onOpenForm) {
                BasicAlertDialog(
                    onDismissRequest = { viewModel.postUiEvent(ProfileUiEvent.OnUpdateStatusForm) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    UpdateProfileInformationForm(
                        uiState = uiState,
                        uiStateForm = uiStateForm,
                        onChangeFirstName = { viewModel.postFormUiEvent(ProfileFormUiEvent.OnChangeFirstName(it)) },
                        onChangeLastName = { viewModel.postFormUiEvent(ProfileFormUiEvent.OnChangeLastName(it)) },
                        onChangeMiddleName = { viewModel.postFormUiEvent(ProfileFormUiEvent.OnChangeMiddleName(it)) },
                        onChangeDateOfBirth = { viewModel.postFormUiEvent(ProfileFormUiEvent.OnChangeDateOfBirth(it)) },
                        onChangeGender = { viewModel.postFormUiEvent(ProfileFormUiEvent.OnChangeGender(it)) },
                        onChangePhoneNumber = { viewModel.postFormUiEvent(ProfileFormUiEvent.OnChangePhoneNumber(it)) },
                        onDismiss = { viewModel.postUiEvent(ProfileUiEvent.OnUpdateStatusForm) },
                        onConfirm = {
                            viewModel.postFormUiEvent(ProfileFormUiEvent.UpdateInfo)
                            viewModel.postUiEvent(ProfileUiEvent.OnUpdateStatusForm)
                        }
                    )
                }
            }
        }
    }
}
