package com.w1nkkkk.meditation.presentation.component.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.w1nkkkk.meditation.domain.auth.AuthRepository
import com.w1nkkkk.meditation.presentation.component.account.AccountViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val accountViewModel: AccountViewModel
) : ViewModel() {

    val state : MutableStateFlow<Throwable?> = MutableStateFlow(null)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, error ->
        state.value = error
    }
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO + coroutineExceptionHandler)

    fun singIn(email : String, password : String) {
        coroutineScope.launch {
            try {
                accountViewModel.user.emit(repository.singIn(email, password))
                state.emit(null)
            } catch (ex: Exception) {
                state.emit(ex)
            }
        }
    }

    fun createUser(email: String, password: String) {
        coroutineScope.launch {
            try {
                accountViewModel.user.emit(repository.createUser(email, password))
                state.emit(null)
            } catch (ex: Exception) {
                state.emit(ex)
            }
        }
    }

    fun singOut() {
        FirebaseAuth.getInstance().signOut()
        coroutineScope.launch {
            state.emit(null)
        }
    }

    fun signInAdmin(login : String, password: String, launchActivity : () -> Unit) {
        coroutineScope.launch {
            if (repository.signInAdmin(login, password)) {
                launchActivity()
            } else {
                throw Exception("Bad data for admin")
            }
        }
    }

    fun clearError() {
        coroutineScope.launch {
            state.emit(null)
        }
    }

}