package com.digital.adminpanel.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digital.adminpanel.R
import com.digital.adminpanel.domain.Quiz

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestEditorScreen(
    onBack : () -> Unit,
    viewModel: TestsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showSaveSuccess by remember { mutableStateOf(false) }
    var hasUnsavedChanges by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Test Editor") },
                navigationIcon = {
                    IconButton(onClick = {
                        if (!hasUnsavedChanges) {
                            onBack()
                        } else {

                        }
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.saveChanges()
                            showSaveSuccess = true
                        },
                        enabled = hasUnsavedChanges
                    ) {
                        // save
                        Icon(painterResource(R.drawable.ic_save), contentDescription = "Save")
                    }
                }
            )
        },
        floatingActionButton = {
            if (hasUnsavedChanges) {
                ExtendedFloatingActionButton(
                    onClick = {
                        viewModel.saveChanges()
                        showSaveSuccess = true
                    },
                    // save
                    icon = { Icon(painterResource(R.drawable.ic_save), "Save") },
                    text = { Text("Save Changes") }
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (val state = uiState) {
                is TestEditorState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is TestEditorState.Error -> {
                    ErrorMessage(message = state.message)
                }
                is TestEditorState.SavedSuccessfully -> {
                    LaunchedEffect(showSaveSuccess) {
                        if (showSaveSuccess) {
                            hasUnsavedChanges = false
                        }
                    }
                    TestEditorContent(
                        quiz = state.quiz,
                        viewModel = viewModel,
                        onDataChanged = { hasUnsavedChanges = true }
                    )
                }
                is TestEditorState.Success -> {
                    TestEditorContent(
                        quiz = state.quiz,
                        viewModel = viewModel,
                        onDataChanged = { hasUnsavedChanges = true }
                    )
                }
            }

            if (showSaveSuccess) {
                Snackbar(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomCenter),
                    action = {
                        TextButton(onClick = { showSaveSuccess = false }) {
                            Text("Dismiss")
                        }
                    }
                ) {
                    Text("Test saved successfully!")
                }
            }
        }
    }
}

@Composable
private fun TestEditorContent(
    quiz: Quiz,
    viewModel: TestsViewModel,
    onDataChanged: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(quiz.questions.entries.toList()) { (questionId, question) ->
            QuestionCard(
                questionId = questionId,
                question = question,
                onQuestionTextChanged = { newText ->
                    viewModel.updateQuestion(questionId, newText)
                    onDataChanged()
                },
                onAnswerPointsChanged = { answerId, newPoints ->
                    viewModel.updateAnswerPoints(questionId, answerId, newPoints)
                    onDataChanged()
                }
            )
        }
    }
}

@Composable
private fun ErrorMessage(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Error",
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}