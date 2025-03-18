package com.w1nkkkk.meditation.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.w1nkkkk.meditation.R
import com.w1nkkkk.meditation.presentation.component.AppTopBar
import com.w1nkkkk.meditation.presentation.component.auth.AuthViewModel
import com.w1nkkkk.meditation.presentation.navigation.Route

@Composable
fun ProfileScreen(
    navController : NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { AppTopBar(navController, mainScreen = false) },
        modifier = Modifier.fillMaxSize()
    ) { containersPadding ->
        Box(modifier = Modifier.fillMaxSize()
            .padding(top = containersPadding.calculateTopPadding()),
        ) {
            IconButton(onClick = {
                authViewModel.singOut()
                navController.navigate(Route.Login.path)
            }, modifier = Modifier.fillMaxWidth().align(Alignment.TopEnd)) {
                Icon(
                    painter = painterResource(R.drawable.ic_logout),
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    val nav = rememberNavController()
    ProfileScreen(nav)
}