package com.w1nkkkk.meditation.presentation.component.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.w1nkkkk.meditation.domain.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    sealed class State {
        class Error(val throwable: Throwable) : State()
        class Sucess(val state : Boolean) : State()
    }

    private val _state : MutableLiveData<State> = MutableLiveData()
    val state : LiveData<State> = _state

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, error ->
        _state.postValue(State.Error(error))
    }
    private val coroutineScope = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler)

    fun singIn(email : String, password : String) {
        coroutineScope.launch {
            val state = repository.singIn(email, password)
            _state.postValue(State.Sucess(state))
        }
    }

    fun createUser(email: String, password: String) {
        coroutineScope.launch {
            val state = repository.createUser(email, password)
            _state.postValue(State.Sucess(state))
        }
    }

    fun singOut() {
        FirebaseAuth.getInstance().signOut()
        _state.value = State.Sucess(false)
    }

}