package com.example.clinicmanagerfront.presentation.view.authScreens.signUpScreen

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.clinicmanagerfront.R
import com.example.clinicmanagerfront.navigation.Screen
import com.example.clinicmanagerfront.presentation.view.authScreens.signUpScreen.uiEvent.SignUpUiEvent
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun SignUpScreen(
    navController: NavHostController,
) {

    val viewModel: SignUpViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.SignUp.route) { inclusive = true }
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
            text = stringResource(R.string.auth_sign_up),
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
                value = uiState.email,
                onValueChange = {
                    viewModel.postUiEvent(SignUpUiEvent.OnEmailChange(it))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = null,
                        tint = BlueText
                    )
                },
                placeholder = {
                    Text(
                        text = "Введите почту",
                        color = Gray900
                    )
                },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .padding(bottom = 6.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.username,
                onValueChange = {
                viewModel.postUiEvent(SignUpUiEvent.OnUsernameChange(it))
                },
                textStyle = Typography.bodyMedium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Face,
                        contentDescription = null,
                        tint = BlueText
                    )
                },
                placeholder = {
                    Text(
                        text = "Введите имя пользователя",
                        color = Gray700
                    )
                },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .padding(bottom = 6.dp, top = 6.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.password,
                onValueChange = {
                    viewModel.postUiEvent(SignUpUiEvent.OnPasswordChange(it))
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
                        viewModel.postUiEvent(SignUpUiEvent.TogglePasswordVisibility)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_visibility_eye),
                            contentDescription = null,
                            tint = BlueText
                        )
                    }
                },
                placeholder = {
                    Text(
                        text = "Введите пароль",
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
                Text(text = "Уже есть аккаунт?")
                Text(
                    text = "Войти",
                    Modifier.clickable{ navController.navigate(Screen.SignIn.route) }
                )
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = { viewModel.postUiEvent(SignUpUiEvent.OnSignUpClick) },
            modifier = Modifier.fillMaxWidth(0.5f),
            colors = ButtonDefaults.buttonColors(
                containerColor = BlueText,
                contentColor = Card
            ),
            contentPadding = PaddingValues(10.5.dp)
        ) {
            Text(text = "Зарегистрироваться")
        }
    }
}