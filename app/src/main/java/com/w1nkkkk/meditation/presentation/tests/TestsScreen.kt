package com.w1nkkkk.meditation.presentation.tests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.digital.adminpanel.data.TestRepositoryImpl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(
    navController: NavController,
    onTestCompleted: (Int) -> Unit,
    viewModel: UserTestViewModel = viewModel(factory = UserTestViewModelFactory(TestRepositoryImpl()))
) {
    val quiz by viewModel.quiz.collectAsState()
    val currentIndex by viewModel.currentQuestionIndex.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()
    val testResult by viewModel.testResult.collectAsState()

    when (loadingState) {
        is UserTestViewModel.LoadingState.Loading -> {
            CenterAlignedTopAppBar(title = { Text("Загрузка теста...") })
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is UserTestViewModel.LoadingState.Error -> {
            val error = (loadingState as UserTestViewModel.LoadingState.Error).message
            ErrorScreen(error = error, onRetry = { viewModel.loadQuiz() })
        }
        is UserTestViewModel.LoadingState.Success -> {
            if (testResult != null) {
                TestResultScreen(
                    score = testResult!!,
                    onFinish = { onTestCompleted(testResult!!) },
                    onRetry = {
                        viewModel.retry()
                    }
                )
            } else {
                quiz?.let {
                    val questions = it.questions.entries.toList()
                    if (currentIndex < questions.size) {
                        val (questionId, question) = questions[currentIndex]
                        var selectedAnswerId = viewModel.selectedAnswers[questionId]
                        QuestionScreen(
                            questionId = questionId,
                            question = question,
                            selectedAnswerId = selectedAnswerId,
                            onAnswerSelected = { answerId ->
                                viewModel.selectAnswer(questionId, answerId)
                                selectedAnswerId = answerId
                            },
                            onNext = {
                                if (currentIndex < questions.size - 1) {
                                    viewModel.nextQuestion()
                                } else {
                                    viewModel.calculateResult()
                                }
                            },
                            onPrev = { viewModel.prevQuestion() },
                            isFirst = currentIndex == 0,
                            isLast = currentIndex == questions.size - 1,
                            currentIndex = currentIndex
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ErrorScreen(error: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ошибка",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.error
        )

        Text(
            text = error,
            modifier = Modifier.padding(vertical = 16.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        Button(onClick = onRetry) {
            Text("Повторить попытку")
        }
    }
}