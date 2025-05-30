package com.digital.adminpanel.presentation

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun PointsTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var currentValue by remember { mutableStateOf(value) }

    OutlinedTextField(
        value = currentValue,
        onValueChange = { newValue ->
            if (newValue.isEmpty()) {
                currentValue = ""
                onValueChange("0") // Устанавливаем 0 если поле пустое
                return@OutlinedTextField
            }

            val numericValue = newValue.filter { it.isDigit() }
            if (numericValue.isNotEmpty()) {
                val intValue = numericValue.toInt()
                if (intValue in 0..20) { // Проверка диапазона
                    currentValue = numericValue
                    onValueChange(numericValue)
                }
            } else {
                currentValue = ""
                onValueChange("0")
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        singleLine = true,
        modifier = modifier.width(100.dp),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        suffix = { Text(text = "/20") } // Индикатор максимального значения
    )
}