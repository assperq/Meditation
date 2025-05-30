package com.w1nkkkk.meditation.presentation.tests

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.digital.adminpanel.domain.Quiz
import com.digital.adminpanel.domain.TestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserTestViewModel @Inject constructor(
    val repository: TestRepository
) : ViewModel() {

    private val _quiz = MutableStateFlow<Quiz?>(null)
    val quiz: StateFlow<Quiz?> = _quiz.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow<Int>(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _selectedAnswers = mutableStateMapOf<String, String>() // <- Автоматически observable
    val selectedAnswers: SnapshotStateMap<String, String> = _selectedAnswers

    private val _testResult = MutableStateFlow<Int?>(null)
    val testResult: StateFlow<Int?> = _testResult.asStateFlow()

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Loading)
    val loadingState: StateFlow<LoadingState> = _loadingState.asStateFlow()

    init {
        loadQuiz()
    }

    fun loadQuiz() {
        viewModelScope.launch {
            _loadingState.value = LoadingState.Loading
            try {
                _quiz.value = repository.getTests()
                _loadingState.value = LoadingState.Success
            } catch (e: Exception) {
                _loadingState.value = LoadingState.Error(e.message ?: "Ошибка загрузки")
            }
        }
    }

    fun selectAnswer(questionId: String, answerId: String) {
        println("Selecting answer: $questionId -> $answerId") // Лог выбора
        _selectedAnswers[questionId] = answerId
        _quiz.value?.let { currentQuiz ->
            val question = currentQuiz.questions[questionId]
            val answer = question?.answers?.get(answerId)
            println("Selected answer points: ${answer?.pointNumber}") // Лог баллов
        }
    }

    fun calculateResult() {
        val currentQuiz = _quiz.value ?: return
        var totalScore = 0

        _selectedAnswers.forEach { (questionId, answerId) ->
            val question = currentQuiz.questions[questionId]
            val answer = question?.answers?.get(answerId)
            totalScore += answer?.pointNumber ?: 0
        }

        _testResult.value = totalScore
    }

    fun nextQuestion() {
        _currentQuestionIndex.value += 1
    }

    fun prevQuestion() {
        _currentQuestionIndex.value -= 1
    }

    fun retry() {
        _testResult.value = null
        _currentQuestionIndex.value = 0
        _selectedAnswers.clear()
    }

    sealed class LoadingState {
        object Loading : LoadingState()
        object Success : LoadingState()
        data class Error(val message: String) : LoadingState()
    }
}

class UserTestViewModelFactory(
    private val repository: TestRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserTestViewModel(repository) as T
    }
}