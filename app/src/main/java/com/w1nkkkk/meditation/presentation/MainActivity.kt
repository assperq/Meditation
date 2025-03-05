package com.w1nkkkk.meditation.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.w1nkkkk.meditation.data.entity.PreferencesModel
import com.w1nkkkk.meditation.domain.preferences.Preferences
import com.w1nkkkk.meditation.presentation.component.preferences.PreferencesPresenter
import com.w1nkkkk.meditation.presentation.component.preferences.PreferencesView
import com.w1nkkkk.meditation.presentation.navigation.Route
import com.w1nkkkk.meditation.presentation.navigation.SetupNavGraph
import com.w1nkkkk.meditation.presentation.theme.MeditationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), PreferencesView {

    @Inject
    lateinit var preferencesPresenter : PreferencesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationTheme {
                val navController = rememberNavController()
                var startDestination = Route.Main.path
                if (FirebaseAuth.getInstance().currentUser == null) {
                    startDestination = Route.Login.path
                }
                SetupNavGraph(navController, preferencesPresenter, startDestination)
            }
        }
    }

    override fun onUpdatePreferences(model: Preferences) {
        preferences.value.meditaitionTime.value = model.meditaitionTime.value
        Log.d("LOG", "VALUE : ${preferences.value.meditaitionTime}")
    }

    companion object {
        val preferences = mutableStateOf(Preferences())
    }
}