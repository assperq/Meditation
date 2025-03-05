package com.w1nkkkk.meditation.presentation.navigation

sealed class Route(val path : String) {
    data object Main : Route("home")
    data object Settings : Route("settings")
    data object Profile : Route("profile")
    data object Login : Route("login")
    data object Registration : Route("registration")
}