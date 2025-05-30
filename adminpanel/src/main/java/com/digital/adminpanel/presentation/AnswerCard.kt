package com.digital.adminpanel.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.digital.adminpanel.domain.Quiz.Question.Answer


@Composable
fun AnswerPointEditor(
    answerId: String,
    answer: Answer,
    onPointsChanged: (Int) -> Unit
) {
    var points by remember { mutableStateOf(answer.pointNumber.toString()) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = answer.answerName,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )

        PointsTextField(
            value = points,
            onValueChange = { newValue ->
                points = newValue
                onPointsChanged(newValue.toIntOrNull() ?: 0)
            }
        )
    }
}
