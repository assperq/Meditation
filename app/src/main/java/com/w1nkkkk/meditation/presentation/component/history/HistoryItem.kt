package com.w1nkkkk.meditation.presentation.component.history

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.w1nkkkk.meditation.domain.history.HistoryModel
import com.w1nkkkk.meditation.domain.mode.BodyScan
import com.w1nkkkk.meditation.presentation.component.BaseText
import com.w1nkkkk.meditation.toLocalDate

@Composable
fun HistoryItem(data : HistoryModel) {
    Card(
        modifier = Modifier.fillMaxWidth().height(60.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 14.dp),
        colors = CardDefaults.cardColors(contentColor = Color.DarkGray)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            BaseText(data.date.toString(), fontSize = 12.sp, modifier = Modifier.padding(start = 7.dp))
            BaseText("${data.type} - ${data.count}",
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically).padding(start = 20.dp))
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun  HistoryItemPreview() {
    HistoryItem(HistoryModel(
        "2000-12-12".toLocalDate(),
        BodyScan().getName(LocalContext.current),
        "10 minutes"
    ))
}