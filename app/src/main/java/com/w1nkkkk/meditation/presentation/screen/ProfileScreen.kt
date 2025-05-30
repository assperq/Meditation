package com.w1nkkkk.meditation.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.w1nkkkk.meditation.R
import com.w1nkkkk.meditation.domain.history.HistoryModel
import com.w1nkkkk.meditation.presentation.component.AppTopBar
import com.w1nkkkk.meditation.presentation.component.BaseText
import com.w1nkkkk.meditation.presentation.component.HorizontalSpace
import com.w1nkkkk.meditation.presentation.component.VerticalSpace
import com.w1nkkkk.meditation.presentation.component.account.AccountViewModel
import com.w1nkkkk.meditation.presentation.component.history.HistoryItem
import com.w1nkkkk.meditation.presentation.component.history.HistoryViewModel
import com.w1nkkkk.meditation.presentation.navigation.Route

@Composable
fun ProfileScreen(
    navController : NavController,
    accountViewModel: AccountViewModel = hiltViewModel(),
    historyViewModel: HistoryViewModel = hiltViewModel()
) {

    val user = accountViewModel.user.collectAsState()
    val historyList = historyViewModel.historyList.collectAsState()
    val userName by remember { mutableStateOf(user.value.name) }

    val goodColor = Color(0xFF4CAF50)
    val mediumColor = Color(0xFFFFC107)
    val badColor = Color(0xFFF44336)

    var emotionImage by remember {
        mutableIntStateOf(R.drawable.good_state)
    }

    val emotion = user.value.emotion_state
    val emotionColor by remember {
        mutableStateOf(
            if (emotion in 0..40) {
                emotionImage = R.drawable.bad_state
                badColor
            } else if (emotion in 41..70) {
                emotionImage = R.drawable.normal_state
                mediumColor
            } else if (emotion in 71..100) {
                emotionImage = R.drawable.good_state
                goodColor
            } else {
                emotionImage = R.drawable.normal_state
                Color.Gray
            }
        )
    }


    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        ChangeUsernameDialog(
            currentName = userName,
            onDismiss = { showDialog = false },
            onConfirm = { newName ->
                accountViewModel.changeName(newName)
                showDialog = false
            }
        )
    }

    Scaffold(
        topBar = { AppTopBar(navController, mainScreen = false) },
        modifier = Modifier.fillMaxSize()
    ) { containersPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(top = containersPadding.calculateTopPadding(), start = 10.dp, end = 10.dp),
        ) {
            Column {
                VerticalSpace()
                Row {
                    Image(
                        painter = painterResource(R.drawable.ic_profile_icon),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(128.dp)
                            .clip(CircleShape)
                    )
                    HorizontalSpace()
                    Column {
                        VerticalSpace()
                        Row(modifier = Modifier.clickable(true, onClick = {
                            showDialog = true
                        })) {
                            BaseText(userName,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp)
                            HorizontalSpace(3.dp)
                            Box(contentAlignment = Alignment.TopStart) {
                                Icon(painter = painterResource(R.drawable.pencil), null, modifier = Modifier.size(14.dp))
                            }
                        }

                        VerticalSpace(10.dp)
                        BaseText("${LocalContext.current.getString(R.string.days_on_the_roll)}: ${user.value.dayCount}", fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp)

                        VerticalSpace(3.dp)
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${LocalContext.current.getString(R.string.emotional_state)}: ${user.value.emotion_state}",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 18.sp,
                                color = emotionColor,
                                modifier = Modifier.weight(1f),
                                softWrap = true // Перенос текста (включено по умолчанию)
                            )
                            HorizontalSpace(3.dp)
                            Image(
                                painter = painterResource(emotionImage),
                                contentDescription = null,
                                modifier = Modifier.size(32.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }

                VerticalSpace(20.dp)

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(onClick = {
                        navController.navigate(Route.Test.path)
                    }, modifier = Modifier.clip(RoundedCornerShape(4.dp))) {
                        BaseText(LocalContext.current.getString(R.string.take_the_test))
                    }
                }

                VerticalSpace(20.dp)
                //HistoryBlock
                Column {
                    Row(modifier = Modifier.clickable(onClick = {
                        navController.navigate(Route.History.path)
                    })) {
                        Image(painter = painterResource(R.drawable.ic_back), contentDescription = null)
                        HorizontalSpace(4.dp)
                        BaseText(LocalContext.current.getString(R.string.view_all_history))
                    }
                    VerticalSpace(4.dp)

                    LazyColumn {
                        var subList : List<HistoryModel> = emptyList()
                        subList = try {
                            historyList.value.subList(0, 3)
                        } catch (_ : Exception) {
                            historyList.value
                        }
                        items(subList) {
                            HistoryItem(it)
                            VerticalSpace(4.dp)
                        }
                        item {
                            VerticalSpace(7.dp)
                        }
                    }
                }

                //AchivmentsBlock
            }
        }
    }
}

@Composable
fun ChangeUsernameDialog(
    currentName: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    val maxCharLimit = 16
    var username by remember { mutableStateOf(currentName) }
    val charCount = username.length
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = context.getString(R.string.change_name)) },
        text = {
            Column {
                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        if (it.length <= maxCharLimit) {
                            username = it
                        }
                    },
                    label = { Text(context.getString(R.string.user_name)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "$charCount/$maxCharLimit",
                    color = if (charCount == maxCharLimit) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(username) },
                enabled = username.isNotBlank() && username != currentName
            ) {
                Text("Сменить")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    )
}

@Composable
@Preview
fun ProfileScreenPreview() {
    val nav = rememberNavController()
    ProfileScreen(nav)
}