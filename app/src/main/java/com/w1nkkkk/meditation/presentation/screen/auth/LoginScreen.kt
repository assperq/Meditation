package com.w1nkkkk.meditation.presentation.screen.auth

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.digital.adminpanel.presentation.AdminActivity
import com.w1nkkkk.meditation.R
import com.w1nkkkk.meditation.presentation.component.ErrorDialog
import com.w1nkkkk.meditation.presentation.component.auth.AuthViewModel
import com.w1nkkkk.meditation.presentation.component.auth.LoginInputs
import com.w1nkkkk.meditation.presentation.component.auth.TitleText
import com.w1nkkkk.meditation.presentation.theme.AppTheme

@Composable
@Preview
fun LoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    onNavigateToRegistration: () -> Unit = {},
    onNavigateToForgotPassword: () -> Unit = {},
) {

    var seeDialog by remember {
        mutableStateOf(false)
    }
    var message by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    LaunchedEffect(authViewModel) {
        authViewModel.state.collect { error ->
            error?.let {
                seeDialog = true
                message = it.localizedMessage ?: "Unknown error"
            }
        }
    }

    if (seeDialog) {
        ErrorDialog(message, "Error",
            onClickOk = {
                seeDialog = false
                authViewModel.clearError()
            }, onDismiss = {
                seeDialog = false
                authViewModel.clearError()
            })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.paddingLarge)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = AppTheme.dimens.paddingLarge)
                    .padding(bottom = AppTheme.dimens.paddingExtraLarge)
            ) {
                TitleText(
                    modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                    text = stringResource(id = R.string.login_heading_text)
                )

                LoginInputs(
                    onEmailOrMobileChange = {},
                    onPasswordChange = {},
                    onSubmit = { email, pass, isAdmin ->
                        if (isAdmin) {
                            authViewModel.signInAdmin(email, pass) {
                                val intent = Intent(context, AdminActivity::class.java)
                                context.startActivity(intent)
                            }
                        } else {
                            authViewModel.singIn(email, pass)
                        }

                    },
                    onForgotPasswordClick = onNavigateToForgotPassword
                )

            }
        }

        Row(
            modifier = Modifier.padding(AppTheme.dimens.paddingNormal),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.do_not_have_account))

            Text(
                modifier = Modifier
                    .padding(start = AppTheme.dimens.paddingExtraSmall)
                    .clickable {
                        onNavigateToRegistration()
                    },
                text = stringResource(id = R.string.register),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}