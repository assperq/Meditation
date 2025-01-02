package com.w1nkkkk.meditation.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.w1nkkkk.meditation.presentation.component.AppTopBar

@Composable
fun ProfileScreen(navController : NavController) {
    Scaffold(
        topBar = { AppTopBar(navController, mainScreen = false) },
        modifier = Modifier.fillMaxSize()
    ) { containersPadding ->
        Box(modifier = Modifier.fillMaxSize()
            .padding(top = containersPadding.calculateTopPadding())
        ) {
            Text(text = "Profile screen")
        }
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    val nav = rememberNavController()
    ProfileScreen(nav)
}