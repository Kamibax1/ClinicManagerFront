package com.example.clinicmanagerfront.presentation.view.homeScreen.homeAddAppointmentForm.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropMenu(dropMenu: DropMenuData<T>) {
    var expanded by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    val filteredItems = dropMenu.items.filter {
        it.toString().contains(message, ignoreCase = true)
    }

    val scrollState = rememberScrollState()

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = message,
            onValueChange = {
                message = it
                expanded = true
            },
            placeholder = { Text(text = dropMenu.title) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        if (filteredItems.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                shape = RoundedCornerShape(12.dp),
                scrollState = scrollState
            ) {
                filteredItems.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.toString()) },
                        onClick = {
                            message = item.toString()
                            expanded = false
                            dropMenu.onItemSelected(item)
                            focusManager.clearFocus()
                        },
                    )
                }
            }
        }
    }
}
