package com.example.clinicmanagerfront.presentation.view.authScreens.signInScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.navigation.Screen
import com.example.clinicmanagerfront.presentation.view.authScreens.signInScreen.uiEvent.SignInUiEvent
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun SignInScreen(
    navController: NavHostController
) {
    val viewModel: SignInViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.SignIn.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 17.5.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.auth_sign_in),
            style = AuthTitleTextStyle,
            modifier = Modifier
                .padding(bottom = 18.dp)
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.size(20.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Card,
                    RoundedCornerShape(12.dp)
                )
                .padding(17.5.dp)
        ) {

            OutlinedTextField(
                value = uiState.username,
                onValueChange = {
                    viewModel.postUiEvent(SignInUiEvent.OnUsernameChange(it))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Face,
                        contentDescription = null,
                        tint = BlueText
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.auth_enter_username),
                        color = Gray900
                    )
                },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .padding(bottom = 6.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.password,
                onValueChange = {
                    viewModel.postUiEvent(SignInUiEvent.OnPasswordChange(it))
                },
                visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        tint = BlueText
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                        viewModel.postUiEvent(SignInUiEvent.TogglePasswordVisibility)
                        }
                    ) {
                        Icon(
                            imageVector = if (uiState.isPasswordVisible)
                                Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null,
                            tint = BlueText
                        )
                    }
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.auth_enter_password),
                        color = Gray900
                    )
                },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .padding(bottom = 6.dp, top = 6.dp)
                    .fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text("Нет аккаунта?")
                Text(
                    text = "Зарегистрироваться",
                    Modifier.clickable{ navController.navigate(Screen.SignUp.route) }
                )
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = { viewModel.postUiEvent(SignInUiEvent.OnSignInClick) },
            modifier = Modifier.fillMaxWidth(0.5f),
            colors = ButtonDefaults.buttonColors(
                containerColor = BlueText,
                contentColor = Card
            ),
            contentPadding = PaddingValues(10.5.dp),
            enabled = !uiState.isLoading
        ) {
            Text(
                text = "Войти",
            )
        }
    }
}
