package com.w1nkkkk.meditation.presentation.screen.auth

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
import androidx.lifecycle.LifecycleOwner
import com.w1nkkkk.meditation.R
import com.w1nkkkk.meditation.presentation.component.auth.AuthViewModel
import com.w1nkkkk.meditation.presentation.component.auth.ErrorDialog
import com.w1nkkkk.meditation.presentation.component.auth.LoginInputs
import com.w1nkkkk.meditation.presentation.component.auth.TitleText
import com.w1nkkkk.meditation.presentation.theme.AppTheme

@Composable
@Preview
fun LoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    onNavigateToRegistration: () -> Unit = {},
    onNavigateToForgotPassword: () -> Unit = {},
    onNavigateToAuthenticatedRoute: () -> Unit = {}
) {

    var seeDialog by remember {
        mutableStateOf(false)
    }
    var message by remember {
        mutableStateOf("")
    }


    authViewModel.state.observe(LocalContext.current as LifecycleOwner) {
        when(it) {
            is AuthViewModel.State.Error -> {
                seeDialog = true
                message = it.throwable.localizedMessage?.toString() ?: ""
            }
            is AuthViewModel.State.Sucess -> {
                if (it.state) {
                    onNavigateToAuthenticatedRoute()
                }
            }
        }
    }

    if (seeDialog) {
        ErrorDialog(message, "Error", onClickOk = { seeDialog = false }) { }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Main card Content for Login
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

                // Login Inputs Composable
                LoginInputs(
                    onEmailOrMobileChange = {},
                    onPasswordChange = {},
                    onSubmit = { email, pass ->
                        authViewModel.singIn(email, pass)
                    },
                    onForgotPasswordClick = onNavigateToForgotPassword
                )

            }
        }

        // Register Section
        Row(
            modifier = Modifier.padding(AppTheme.dimens.paddingNormal),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Don't have an account?
            Text(text = stringResource(id = R.string.do_not_have_account))

            //Register
            Text(
                modifier = Modifier
                    .padding(start = AppTheme.dimens.paddingExtraSmall)
                    .clickable {
                        onNavigateToRegistration.invoke()
                    },
                text = stringResource(id = R.string.register),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}