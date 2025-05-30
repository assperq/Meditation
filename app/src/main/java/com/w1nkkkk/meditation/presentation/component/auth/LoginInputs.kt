package com.w1nkkkk.meditation.presentation.component.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.w1nkkkk.meditation.R
import com.w1nkkkk.meditation.presentation.component.BaseText
import com.w1nkkkk.meditation.presentation.theme.AppTheme


@Composable
fun LoginInputs(
    onEmailOrMobileChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: (email: String, password : String, isAdmin : Boolean) -> Unit,
    onForgotPasswordClick: () -> Unit
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        EmailTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = email,
            onValueChange = {
                email = it
            },
            label = stringResource(id = R.string.login_email_id_or_phone_label),
            errorText = stringResource(id = R.string.login_error_msg_empty_email_mobile)
        )


        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = password,
            onValueChange = {
                password = it
            },
            label = stringResource(id = R.string.login_password_label),
            errorText = stringResource(id = R.string.login_error_msg_empty_password),
            imeAction = ImeAction.Done
        )

        Text(
            modifier = Modifier
                .padding(top = AppTheme.dimens.paddingSmall)
                .align(alignment = Alignment.End)
                .clickable {
                    onForgotPasswordClick.invoke()
                },
            text = stringResource(id = R.string.forgot_password),
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyMedium
        )

        var checkedState by remember {
            mutableStateOf(false)
        }

        Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.width(250.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Checkbox(
                    checkedState,
                    onCheckedChange = { checkedState = it }
                )
                BaseText(
                    LocalContext.current.getString(R.string.admin), fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // Login Submit Button
        NormalButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
            text = stringResource(id = R.string.login_button_text),
            onClick = {
                onSubmit(email, password, checkedState)
            }
        )

    }
}