package com.w1nkkkk.meditation.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.w1nkkkk.meditation.R
import com.w1nkkkk.meditation.presentation.MainActivity
import com.w1nkkkk.meditation.presentation.component.AppTopBar
import com.w1nkkkk.meditation.presentation.component.BaseText
import com.w1nkkkk.meditation.presentation.component.auth.AuthViewModel
import com.w1nkkkk.meditation.presentation.component.preferences.PreferencesPresenter

@Composable
fun SettingsScreen(navController : NavController,
   preferencesPresenter: PreferencesPresenter,
   authViewModel: AuthViewModel = hiltViewModel()
) {
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
                    BaseText(context.getString(R.string.meditation_time_preference) + ": ", fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold)
                    BaseText("%.0f".format(meditationTime), fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
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
                Spacer(modifier = Modifier.height(7.dp))

                SettingsSection(
                    iconPlaceholder = R.drawable.ic_logout,
                    content = {
                        SettingElement(
                            text = context.getString(R.string.log_out_button),
                            onElementClick = {
                                authViewModel.singOut()
                            }
                        )
                    }
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


@Composable
private fun SettingsSection(
    iconPlaceholder: Int,
    content: @Composable () -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(iconPlaceholder), contentDescription = null,
                    modifier = Modifier.size(48.dp))
            }

            Spacer(Modifier.padding(vertical = 4.dp))

            content()
        }
    }
}

@Composable
private fun SettingElement(
    text : String,
    onElementClick : () -> Unit
) {
    Box(
        modifier = Modifier
            .width(250.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(
                width = 1.dp,
                color = Color(146, 146, 146),
                shape = RoundedCornerShape(20.dp)
            )
            .background(Color.White)
            .clickable(onClick = onElementClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(10.dp).clip(RoundedCornerShape(70.dp))
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}
