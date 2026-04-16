package com.example.clinicmanagerfront.presentation.view.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicmanagerfront.ui.theme.*

@Composable
fun PersonalCard(
    onOpenForm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp, shape = RoundedCornerShape(12.dp))
            .background(color = MainContentCard)
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .background(
                            color = Primary,
                            shape = CircleShape
                        )
                        .size(80.dp),
                    contentAlignment = Alignment.Center,
                ){
                    Icon(
                        imageVector = Icons.Outlined.PersonOutline,
                        contentDescription = null,
                        tint = Card,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Column{
                    Text(
                        text = "Dr.Anderson",
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 20.sp,
                            color = Card
                        )
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "Администратор",
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 14.sp,
                            color = Card
                        )
                    )
                }
            }
            IconButton(
                onClick = onOpenForm,
                modifier = Modifier.size(32.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Primary,
                    contentColor = Card
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.EditNote,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Primary,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        ) {
            InfoPerson(Icons.Outlined.Mail, "anderson@clinic.ru")
            Spacer(modifier = Modifier.size(8.dp))
            InfoPerson(Icons.Outlined.Phone, "+7 (999) 000-00-00")
            Spacer(modifier = Modifier.size(8.dp))
            InfoPerson(Icons.Outlined.MedicalServices, "Clinic Manager")
        }
    }
}

@Composable
fun InfoPerson(icon: ImageVector, text: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Card,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 14.sp,
                color = Card
            )
        )
    }
}
