package com.w1nkkkk.meditation.presentation.screen

import android.content.Intent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.w1nkkkk.meditation.R
import com.w1nkkkk.meditation.domain.mode.BodyScan
import com.w1nkkkk.meditation.domain.mode.LovingKindnessMeditation
import com.w1nkkkk.meditation.domain.mode.MeditationMode
import com.w1nkkkk.meditation.domain.mode.MindfulBreathing
import com.w1nkkkk.meditation.domain.mode.NatureSoundMeditation
import com.w1nkkkk.meditation.domain.mode.SilentMeditation
import com.w1nkkkk.meditation.domain.mode.Visualization
import com.w1nkkkk.meditation.domain.mode.WalkingMeditation
import com.w1nkkkk.meditation.domain.preferences.Preferences
import com.w1nkkkk.meditation.domain.services.MeditationService
import com.w1nkkkk.meditation.presentation.MainActivity
import com.w1nkkkk.meditation.presentation.component.AppTopBar
import com.w1nkkkk.meditation.presentation.component.BaseText
import com.w1nkkkk.meditation.presentation.component.DateObject
import com.w1nkkkk.meditation.presentation.component.VerticalSpace
import com.w1nkkkk.meditation.presentation.component.account.AccountViewModel
import com.w1nkkkk.meditation.presentation.component.preferences.PreferencesPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    navController : NavController,
    accountViewModel : AccountViewModel = hiltViewModel(),
    preferencesPresenter: PreferencesPresenter
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

            var currentIndex by remember { mutableStateOf(0) }

            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Стрелка влево
                    IconButton(
                        onClick = {
                            currentIndex =
                                if (currentIndex - 1 >= 0) currentIndex - 1 else modes.size - 1
                        },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Previous mode"
                        )
                    }

                    // Карусель
                    AnimatedContent(
                        targetState = currentIndex,
                        modifier = Modifier.weight(1f),
                        transitionSpec = {
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) with
                                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                        }
                    ) { index ->
                        val currentMode = modes[index]

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(currentMode.image),
                                null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(128.dp)
                            )
                            VerticalSpace(4.dp)
                            BaseText(currentMode.getName(context))
                            VerticalSpace(4.dp)
                            BaseText(currentMode.getDescription(context), textAlign = TextAlign.Center)
                        }
                    }

                    // Стрелка вправо
                    IconButton(
                        onClick = {
                            currentIndex = (currentIndex + 1) % modes.size
                        },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Next mode"
                        )
                    }
                }

                VerticalSpace(8.dp)

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
                ) {
                    items(modes.size) { index ->
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(
                                    if (index == currentIndex) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                )
                                .clickable { currentIndex = index }
                        )
                    }
                }

                VerticalSpace(10.dp)

                val startMeditation = {
                    accountViewModel.changeDaysCount()
                    val today = DateObject.convertLongToTime(DateObject.currentTimeToLong())
                    CoroutineScope(Dispatchers.IO).launch {
                        preferencesPresenter.setPreferences(
                            Preferences(
                                MainActivity.preferences.value.meditaitionTime,
                                false,
                                today
                            )
                        )
                    }
                    val intent = Intent(context, MeditationService::class.java).apply {
                        putExtra(MeditationMode.parcelableName, modes[currentIndex])
                    }
                    ContextCompat.startForegroundService(context, intent)
                }

                val stopMeditation = {
                    val intent = Intent(context, MeditationService::class.java)
                    context.stopService(intent)
                }

                var buttonText by remember {
                    mutableStateOf(context.getString(R.string.start_meditation))
                }

                Button(onClick = {
                    if(MeditationService.isRunning) {
                        stopMeditation()
                        buttonText = context.getString(R.string.start_meditation)
                    }
                    else  {
                        startMeditation()
                        buttonText = context.getString(R.string.stop_meditation)
                    }
                }) {
                    Text(text = buttonText)
                }
            }
        }
    }
}


