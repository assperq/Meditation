package com.w1nkkkk.meditation.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.w1nkkkk.meditation.R
import com.w1nkkkk.meditation.presentation.component.AppTopBar
import com.w1nkkkk.meditation.presentation.component.HorizontalSpace
import com.w1nkkkk.meditation.presentation.component.VerticalSpace
import com.w1nkkkk.meditation.presentation.navigation.Route

@Composable
fun ProfileScreen(
    navController : NavController,
    //authViewModel: AuthViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { AppTopBar(navController, mainScreen = false) },
        modifier = Modifier.fillMaxSize()
    ) { containersPadding ->
        Box(modifier = Modifier.fillMaxSize()
            .padding(top = containersPadding.calculateTopPadding(), start = 10.dp),
        ) {
            Column {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    IconButton(onClick = {
                        //authViewModel.singOut()
                        navController.navigate(Route.Login.path)
                    }) {
                        Image(
                            painter = painterResource(R.drawable.ic_logout),
                            contentDescription = "",
                            modifier = Modifier
                                .size(64.dp)
                        )
                    }
                }
                VerticalSpace()
                Row {
                    Image(
                        painter = painterResource(R.drawable.ic_profile_icon),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,            // crop the image if it's not a square
                        modifier = Modifier
                            .size(128.dp)
                            .clip(CircleShape)                       // clip to the circle shape
                    )
                    HorizontalSpace()
                    Column {

                    }
                }
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