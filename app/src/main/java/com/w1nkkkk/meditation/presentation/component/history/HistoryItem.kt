package com.w1nkkkk.meditation.presentation.component.history

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.w1nkkkk.meditation.domain.history.HistoryModel
import com.w1nkkkk.meditation.presentation.component.BaseText

@Composable
fun HistoryItem(data : HistoryModel) {
    Card(
        modifier = Modifier.fillMaxWidth().height(60.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 14.dp),
        colors = CardDefaults.cardColors(contentColor = Color.DarkGray)
    ) {
        Row(modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BaseText(data.date.toString(), fontSize = 12.sp, modifier = Modifier.padding(start = 7.dp))
            BaseText("${data.type} - ${data.count}",modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.width(48.dp))
        }

    }

}
