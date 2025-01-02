package com.w1nkkkk.meditation.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.w1nkkkk.meditation.presentation.screen.MainScreen
import com.w1nkkkk.meditation.presentation.screen.ProfileScreen
import com.w1nkkkk.meditation.presentation.screen.SettingsScreen

@Composable
fun SetupNavGraph(navController: NavHostController, startDestination : String = Route.Main.path) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route.Main.path) {
            MainScreen(navController = navController)
        }

        composable(Route.Settings.path) {
            SettingsScreen(navController)
        }

        composable(Route.Profile.path) {
            ProfileScreen(navController)
        }
    }
}

