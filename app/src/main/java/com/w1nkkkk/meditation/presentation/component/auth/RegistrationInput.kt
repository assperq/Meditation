package com.w1nkkkk.meditation.presentation.component.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.w1nkkkk.meditation.R
import com.w1nkkkk.meditation.presentation.theme.AppTheme

@Composable
fun RegistrationInputs(
    onSubmit: (String, String) -> Unit,
) {
    // Login Inputs Section
    Column(modifier = Modifier.fillMaxWidth()) {

        var email by remember {
            mutableStateOf("")
        }
        var firstPass by remember {
            mutableStateOf("")
        }
        var secondPass by remember {
            mutableStateOf("")
        }

        val context = LocalContext.current

        // Email ID
        EmailTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = email,
            onValueChange = {
                email = it
            },
            label = stringResource(id = R.string.registration_email_label),
            errorText = stringResource(id = R.string.registration_error_msg_empty_email),
            imeAction = ImeAction.Next
        )

        // Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = firstPass,
            onValueChange = {
                firstPass = it
            },
            label = stringResource(id = R.string.registration_password_label),
            errorText = stringResource(id = R.string.registration_error_msg_empty_password),
            imeAction = ImeAction.Next
        )

        // Confirm Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = secondPass,
            onValueChange = {
                secondPass = it
            },
            label = stringResource(id = R.string.registration_confirm_password_label),
            errorText = stringResource(id = R.string.registration_error_msg_password_mismatch),
            imeAction = ImeAction.Done
        )

        // Registration Submit Button
        NormalButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingExtraLarge),
            text = stringResource(id = R.string.registration_button_text),
            onClick = {
                if (firstPass == secondPass) {
                    onSubmit(email, firstPass)
                    email = ""
                    firstPass = ""
                    secondPass = ""
                }
                else {
                    Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}