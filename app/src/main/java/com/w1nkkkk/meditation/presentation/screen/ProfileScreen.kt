package com.w1nkkkk.meditation.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.w1nkkkk.meditation.presentation.component.auth.AuthViewModel
import com.w1nkkkk.meditation.presentation.component.history.HistoryItem
import com.w1nkkkk.meditation.presentation.component.history.HistoryViewModel
import com.w1nkkkk.meditation.presentation.navigation.Route

@Composable
fun ProfileScreen(
    navController : NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
    accountViewModel: AccountViewModel = hiltViewModel(),
    historyViewModel: HistoryViewModel = hiltViewModel()
) {

    val user = accountViewModel.user.collectAsState()
    val historyList = historyViewModel.historyList.collectAsState()

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
                        BaseText(user.value.name,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 30.sp)
                        VerticalSpace(10.dp)
                        BaseText("${LocalContext.current.getString(R.string.days_on_the_roll)}: ${user.value.dayCount}", fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp)
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
                        try {
                            subList = historyList.value.subList(0, 3)
                        } catch (_ : Exception) {
                            subList = historyList.value
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
@Preview
fun ProfileScreenPreview() {
    val nav = rememberNavController()
    ProfileScreen(nav)
}