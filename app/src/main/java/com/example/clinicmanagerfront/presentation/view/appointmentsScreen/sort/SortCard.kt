package com.example.clinicmanagerfront.presentation.view.appointmentsScreen.sort

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.ui.theme.BlueText
import com.example.clinicmanagerfront.ui.theme.Card
import com.example.clinicmanagerfront.ui.theme.Gray900

@Composable
fun SortCard(
    text: String,
    isSelected: Boolean,
    onSelect: () -> Unit
){
    Button(
        onClick = onSelect,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) BlueText else Card
        ),
        contentPadding = PaddingValues(16.dp, 8.dp),
        shape = RoundedCornerShape(12.dp)
    ){
        Text(
            text = text,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp,
                color = if (isSelected) Card else Gray900
            )
        )
    }
}
