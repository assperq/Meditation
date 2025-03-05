package com.w1nkkkk.meditation.presentation.component.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.w1nkkkk.meditation.domain.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _state : MutableLiveData<Boolean> = MutableLiveData()
    val state : LiveData<Boolean> = _state

    fun singIn(email : String, password : String) {
        viewModelScope.launch {
            _state.postValue(repository.singIn(email, password))
        }

    }

    fun createUser(email: String, password: String) {
        viewModelScope.launch {
            _state.postValue(repository.createUser(email, password))
        }
    }
}