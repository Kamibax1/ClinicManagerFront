package com.example.clinicmanagerfront.presentation.view.common.sort

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.ui.theme.BlueText
import com.example.clinicmanagerfront.ui.theme.Card
import com.example.clinicmanagerfront.ui.theme.GrayText

@Composable
fun SortButton(
    text: String,
    isSelected: Boolean,
    onSelect: () -> Unit
){
    Button(
        onClick = onSelect,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) BlueText else Card
        ),
        contentPadding = PaddingValues(14.dp, 7.dp),
        shape = RoundedCornerShape(12.dp)
    ){
        Text(
            text = text,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.25.sp,
                color = if (isSelected) Card else GrayText
            )
        )
    }
}
