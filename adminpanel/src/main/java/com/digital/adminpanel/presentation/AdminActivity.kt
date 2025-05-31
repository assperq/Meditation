package com.digital.adminpanel.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.digital.adminpanel.presentation.theme.MeditationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding())) {
                        TestEditorScreen(onBack = {
                            finish()
                        })
                    }
                }
            }
        }
    }
}

