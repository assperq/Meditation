package com.w1nkkkk.meditation.presentation.screen

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.w1nkkkk.meditation.R
import com.w1nkkkk.meditation.domain.mode.BodyScan
import com.w1nkkkk.meditation.domain.mode.LovingKindnessMeditation
import com.w1nkkkk.meditation.domain.mode.MeditationMode
import com.w1nkkkk.meditation.domain.mode.MindfulBreathing
import com.w1nkkkk.meditation.domain.mode.NatureSoundMeditation
import com.w1nkkkk.meditation.domain.mode.SilentMeditation
import com.w1nkkkk.meditation.domain.mode.Visualization
import com.w1nkkkk.meditation.domain.mode.WalkingMeditation
import com.w1nkkkk.meditation.domain.services.MeditationService
import com.w1nkkkk.meditation.presentation.component.AppTopBar
import com.w1nkkkk.meditation.presentation.component.account.AccountViewModel

@Composable
fun MainScreen(
    navController : NavController,
    accountViewModel : AccountViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { AppTopBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { containersPadding ->
        Box(modifier = Modifier.fillMaxSize()
            .padding(top = containersPadding.calculateTopPadding(), start = 10.dp, end = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            val modes : Array<MeditationMode> = arrayOf(MindfulBreathing(),
                BodyScan(), LovingKindnessMeditation(), WalkingMeditation(),
                Visualization(), NatureSoundMeditation(), SilentMeditation())

            var currentMode : MeditationMode by remember {
                mutableStateOf(modes[0])
            }

            Button(onClick = {
                accountViewModel.changeDaysCount()
                val intent = Intent(context, MeditationService::class.java).apply {
                    putExtra(MeditationMode.parcelableName, currentMode)
                }
                ContextCompat.startForegroundService(context, intent)
            }) {
                Text(context.getString(R.string.start_meditation))
            }

        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    val nav = rememberNavController()
    MainScreen(nav)
}