package com.w1nkkkk.meditation.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.w1nkkkk.meditation.domain.history.HistoryModel
import com.w1nkkkk.meditation.presentation.component.AppTopBar
import com.w1nkkkk.meditation.presentation.component.VerticalSpace
import com.w1nkkkk.meditation.presentation.component.history.HistoryItem

@Composable
fun HistoryScreen(historyList: List<HistoryModel>, navController : NavController) {
    Scaffold(
        topBar = { AppTopBar(navController, mainScreen = false) },
        modifier = Modifier.fillMaxSize()
    ) { containersPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(
                    top = containersPadding.calculateTopPadding() + 10.dp,
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            LazyColumn {
                items(historyList) {
                    HistoryItem(it)
                    VerticalSpace(6.dp)
                }
            }
        }
    }
}