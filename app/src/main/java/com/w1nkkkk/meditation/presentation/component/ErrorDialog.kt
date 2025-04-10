package com.w1nkkkk.meditation.presentation.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun ErrorDialog(message: String, title: String, onClickOk : () -> Unit, onDismiss : () -> Unit) {
    AlertDialog(
        onDismissRequest = { onClickOk() },
        title = { BaseText(title) },
        text = { BaseText(message) },
        confirmButton = {
            Button({ onClickOk() }) {
                BaseText("OK", fontSize = 22.sp)
            }
        }
    )
}