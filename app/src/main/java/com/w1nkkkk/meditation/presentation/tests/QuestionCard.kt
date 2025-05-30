package com.w1nkkkk.meditation.presentation.tests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.digital.adminpanel.domain.Quiz.Question


@Composable
fun QuestionScreen(
    questionId: String,
    question: Question,
    selectedAnswerId: String?,
    onAnswerSelected: (String) -> Unit,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    isFirst: Boolean,
    isLast: Boolean,
    currentIndex : Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Прогресс-бар
        LinearProgressIndicator(
            progress = { (currentIndex + 1).toFloat() / 5f },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Вопрос
        Text(
            text = question.name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Варианты ответов
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            question.answers.forEach { (answerId, answer) ->
                // Упрощенная версия без Crossfade
                AnswerCard(
                    answer = answer,
                    isSelected = answerId == selectedAnswerId,
                    onClick = { onAnswerSelected(answerId) }
                )
            }
        }

        // Кнопки навигации
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (!isFirst) {
                Button(onClick = onPrev) {
                    Text("Назад")
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            Button(
                onClick = onNext,
                enabled = selectedAnswerId != null
            ) {
                Text(if (isLast) "Завершить" else "Далее")
            }
        }
    }
}