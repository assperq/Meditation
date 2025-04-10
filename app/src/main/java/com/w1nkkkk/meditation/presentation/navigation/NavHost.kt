package com.w1nkkkk.meditation.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.w1nkkkk.meditation.presentation.component.account.AccountViewModel
import com.w1nkkkk.meditation.presentation.component.preferences.PreferencesPresenter
import com.w1nkkkk.meditation.presentation.screen.MainScreen
import com.w1nkkkk.meditation.presentation.screen.ProfileScreen
import com.w1nkkkk.meditation.presentation.screen.SettingsScreen
import com.w1nkkkk.meditation.presentation.screen.auth.LoginScreen
import com.w1nkkkk.meditation.presentation.screen.auth.RegistrationScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    preferencesPresenter : PreferencesPresenter,
    startDestination : String,
    accountViewModel: AccountViewModel = hiltViewModel()
) {
    LaunchedEffect(accountViewModel) {
        accountViewModel.user.collect {
            if (FirebaseAuth.getInstance().currentUser != null) {
                navController.navigate(Route.Main.path)
            }
        }
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route.Main.path) {
            MainScreen(navController = navController)
        }

        composable(Route.Settings.path) {
            SettingsScreen(navController, preferencesPresenter)
        }

        composable(Route.Profile.path) {
            ProfileScreen(navController)
        }

        composable(Route.Login.path) {
            LoginScreen(onNavigateToRegistration = {
                navController.navigate(Route.Registration.path)
            })
        }

        composable(Route.Registration.path) {
            RegistrationScreen(onNavigateBack = {
                navController.popBackStack()
            })
        }
    }
}

