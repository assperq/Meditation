package com.w1nkkkk.meditation.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.w1nkkkk.meditation.R
import com.w1nkkkk.meditation.presentation.MainActivity
import com.w1nkkkk.meditation.presentation.component.AppTopBar
import com.w1nkkkk.meditation.presentation.component.BaseText
import com.w1nkkkk.meditation.presentation.component.preferences.PreferencesPresenter

@Composable
fun SettingsScreen(navController : NavController, preferencesPresenter: PreferencesPresenter) {
    preferencesPresenter.getPreferences()
    val context = LocalContext.current
    val preferences by remember {
        MainActivity.preferences
    }
    var meditationTime by remember {
        preferences.meditaitionTime
    }
    Scaffold(
        topBar = { AppTopBar(navController, mainScreen = false) },
        modifier = Modifier.fillMaxSize()
    ) { containersPadding ->
        Box(modifier = Modifier.fillMaxSize()
            .padding(top = containersPadding.calculateTopPadding() + 10.dp, start = 10.dp, end = 10.dp)
        ) {
            Column {
                Row(modifier = Modifier.fillMaxWidth()) {
                    BaseText(context.getString(R.string.meditation_time_preference) + ": ", fontSize = 22.sp)
                    BaseText("%.0f".format(meditationTime), fontSize = 22.sp)
                }

                Slider(
                    value = meditationTime,
                    onValueChange = {
                        meditationTime = it
                    },
                    onValueChangeFinished = {
                        preferences.meditaitionTime.value = meditationTime
                        preferencesPresenter.setPreferences(preferences)
                    },
                    valueRange = 0f..10f,
                    modifier = Modifier.fillMaxWidth().padding(start = 30.dp, end = 30.dp)
                )

                Spacer(modifier = Modifier.height(5.dp))
                HorizontalDivider(color = Color.Gray)

                Box(
                    modifier = Modifier.fillMaxSize().padding(bottom = 10.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    BaseText("Version 0.0", color = Color.Gray)
                }
            }
        }
    }
}