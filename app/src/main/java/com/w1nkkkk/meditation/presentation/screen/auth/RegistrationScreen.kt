package com.w1nkkkk.meditation.presentation.screen.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.w1nkkkk.meditation.R
import com.w1nkkkk.meditation.presentation.component.ErrorDialog
import com.w1nkkkk.meditation.presentation.component.auth.AuthViewModel
import com.w1nkkkk.meditation.presentation.component.auth.RegistrationInputs
import com.w1nkkkk.meditation.presentation.component.auth.SmallClickableWithIconAndText
import com.w1nkkkk.meditation.presentation.component.auth.TitleText
import com.w1nkkkk.meditation.presentation.theme.AppTheme

@Composable
fun RegistrationScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {

    var seeDialog by remember {
        mutableStateOf(false)
    }
    var message by remember {
        mutableStateOf("")
    }

    LaunchedEffect(authViewModel) {
        authViewModel.state.collect { error ->
            error?.let {
                seeDialog = true
                message = it.localizedMessage ?: "Unknown error"
            }
        }
    }

    if (seeDialog) {
        ErrorDialog(message, "Error", onClickOk = { seeDialog = false }) { seeDialog = false }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {

        // Back Button Icon
        SmallClickableWithIconAndText(
            modifier = Modifier
                .padding(horizontal = AppTheme.dimens.paddingLarge)
                .padding(top = AppTheme.dimens.paddingLarge),
            iconContentDescription = stringResource(id = R.string.navigate_back),
            iconVector = Icons.Outlined.ArrowBack,
            text = stringResource(id = R.string.back_to_login),
            onClick = onNavigateBack
        )


        // Main card Content for Registration
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
                    text = stringResource(id = R.string.registration_heading_text)
                )

                RegistrationInputs(
                    onSubmit = { email, pass ->
                        authViewModel.createUser(email, pass)
                    }
                )
            }
        }
    }
}