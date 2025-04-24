package com.w1nkkkk.meditation.presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.w1nkkkk.meditation.R
import com.w1nkkkk.meditation.presentation.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navController: NavController, mainScreen : Boolean = true) {
//    val topColor = TopAppBarDefaults.topAppBarColors(
//        containerColor = containerColor,
//        navigationIconContentColor = settingsIconColor,
//        actionIconContentColor = profileIconColor
//    )
    val iconSize = 120.dp
    if (mainScreen) {
        TopAppBar(
            title = {},
            actions = {
                IconButton(onClick = { navController.navigate(Route.Profile.path) }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_profile),
                        contentDescription = "Profile", modifier = Modifier.size(iconSize)
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate(Route.Settings.path) }) {
                    Icon(painter = painterResource(R.drawable.ic_settings),
                        contentDescription = "Settings", modifier = Modifier.size(iconSize))
                }
            },
            //colors = topColor
        )
    }
    else {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(painter = painterResource(R.drawable.ic_back),
                        contentDescription = "Back", modifier = Modifier.size(iconSize))
                }
            },
            //colors = topColor
        )
    }
}