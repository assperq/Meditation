package com.digital.adminpanel.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digital.adminpanel.domain.Quiz
import com.digital.adminpanel.domain.TestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestsViewModel @Inject constructor(
    private val repository: TestRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<TestEditorState>(TestEditorState.Loading)
    val uiState: StateFlow<TestEditorState> = _uiState

    private var currentQuiz: Quiz = Quiz()

    init {
        loadTests()
    }

    private fun loadTests() {
        viewModelScope.launch {
            _uiState.value = TestEditorState.Loading
            try {
                currentQuiz = repository.getTests()
                _uiState.value = TestEditorState.Success(currentQuiz)
            } catch (e: Exception) {
                _uiState.value = TestEditorState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun updateQuestion(questionId: String, newText: String) {
        currentQuiz.questions[questionId]?.name = newText
        _uiState.value = TestEditorState.Success(currentQuiz)
    }

    fun updateAnswerPoints(questionId: String, answerId: String, newPoints: Int) {
        currentQuiz.questions[questionId]?.answers?.get(answerId)?.pointNumber = newPoints
        _uiState.value = TestEditorState.Success(currentQuiz)
    }

    fun saveChanges() {
        viewModelScope.launch {
            try {
                repository.setTests(currentQuiz)
                _uiState.value = TestEditorState.SavedSuccessfully(currentQuiz)
            } catch (e: Exception) {
                _uiState.value = TestEditorState.Error("Save failed: ${e.message}")
            }
        }
    }
}

sealed class TestEditorState {
    object Loading : TestEditorState()
    class SavedSuccessfully(val quiz: Quiz) : TestEditorState()
    data class Error(val message: String) : TestEditorState()
    data class Success(val quiz: Quiz) : TestEditorState()
}
