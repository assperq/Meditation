package com.digital.adminpanel.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.digital.adminpanel.domain.Quiz.Question
import kotlin.collections.component1
import kotlin.collections.component2

@Composable
fun QuestionCard(
    questionId: String,
    question: Question,
    onQuestionTextChanged: (String) -> Unit,
    onAnswerPointsChanged: (String, Int) -> Unit
) {
    var questionText by remember { mutableStateOf(question.name) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Question ID: $questionId",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = questionText,
                onValueChange = {
                    questionText = it
                    onQuestionTextChanged(it)
                },
                label = { Text("Question Text") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Answers:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                question.answers.forEach { (answerId, answer) ->
                    AnswerPointEditor(
                        answerId = answerId,
                        answer = answer,
                        onPointsChanged = { newPoints ->
                            onAnswerPointsChanged(answerId, newPoints)
                        }
                    )
                }
            }
        }
    }
}
