package com.w1nkkkk.meditation.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.w1nkkkk.meditation.domain.mode.BodyScan
import com.w1nkkkk.meditation.domain.mode.LovingKindnessMeditation
import com.w1nkkkk.meditation.domain.mode.MeditationMode
import com.w1nkkkk.meditation.domain.mode.MindfulBreathing
import com.w1nkkkk.meditation.domain.mode.NatureSoundMeditation
import com.w1nkkkk.meditation.domain.mode.SilentMeditation
import com.w1nkkkk.meditation.domain.mode.Visualization
import com.w1nkkkk.meditation.domain.mode.WalkingMeditation
import com.w1nkkkk.meditation.presentation.component.AppTopBar

@Composable
fun MainScreen(navController : NavController) {
    Scaffold(
        topBar = { AppTopBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { containersPadding ->
        Box(modifier = Modifier.fillMaxSize()
            .padding(top = containersPadding.calculateTopPadding()),
            contentAlignment = Alignment.Center
        ) {
            val array : Array<MeditationMode> = arrayOf(MindfulBreathing(),
                BodyScan(), LovingKindnessMeditation(), WalkingMeditation(),
                Visualization(), NatureSoundMeditation(), SilentMeditation()
            )


        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    val nav = rememberNavController()
    MainScreen(nav)
}