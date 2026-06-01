package com.example.clinicmanagerfront.presentation.view.profileScreen.profileStats

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.presentation.view.profileScreen.uiState.ProfileUiState
import com.example.clinicmanagerfront.ui.theme.BlueText

@Composable
fun ProfileStats(
    uiState: ProfileUiState
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CardStats(
                CardData(
                    count = {
                        when(uiState.isLoadingStats){
                            false -> {
                                Text(
                                    text = uiState.countAppointment.toString(),
                                    style = TextStyle(
                                        fontFamily = FontFamily.SansSerif,
                                        fontSize = 24.sp,
                                        color = BlueText
                                    )
                                )
                            }
                            true -> {
                                CircularProgressIndicator()
                            }
                        }
                    },
                    title = "Ваши записей"
                ),
                Modifier.weight(1f))
            CardStats(
                CardData(
                    count = {
                        when(uiState.isLoadingStats){
                            false -> {
                                Text(
                                    text = uiState.countCurrentAppointment.toString(),
                                    style = TextStyle(
                                        fontFamily = FontFamily.SansSerif,
                                        fontSize = 24.sp,
                                        color = BlueText
                                    )
                                )
                            }
                            true -> {
                                CircularProgressIndicator()
                            }
                        }
                    },
                    title = "Текущие записей"
                ),
                Modifier.weight(1f))
        }
        CardStats(
            CardData(
                count = {
                    when(uiState.isLoadingStats){
                        false -> {
                            Text(
                                text = uiState.dateRegister ?: "Нет данных",
                                style = TextStyle(
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 24.sp,
                                    color = BlueText
                                )
                            )
                        }
                        true -> {
                            CircularProgressIndicator()
                        }
                    }
                },
                "Дата вашей регистрации"
            ),
            Modifier.fillMaxWidth()
        )
    }
}
