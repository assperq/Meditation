package com.w1nkkkk.meditation.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.w1nkkkk.meditation.presentation.navigation.SetupNavGraph
import com.w1nkkkk.meditation.presentation.theme.MeditationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController)
            }
        }
    }
}