package com.w1nkkkk.meditation.presentation.component.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.w1nkkkk.meditation.domain.account.AccountModel
import com.w1nkkkk.meditation.domain.account.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repository: AccountRepository,
) : ViewModel() {

    private val _user : MutableLiveData<AccountModel> = MutableLiveData()
    val user : LiveData<AccountModel> = _user

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        if (FirebaseAuth.getInstance().uid != null) {
            getAccountData(FirebaseAuth.getInstance().uid!!)
        }
    }

    fun getAccountData(id: String) {
        coroutineScope.launch {
            _user.postValue(repository.getAccountData(id))
        }
    }

    fun changeName(name : String) {
        coroutineScope.launch {
            repository.changeName(name)
        }
        _user.value = AccountModel(
            name,
            _user.value!!.dayCount,
            _user.value!!.achievements
        )
    }

    fun changeDaysCount(date: Long) {
        coroutineScope.launch {
            repository.changeDaysCount(date)
        }
        _user.value = AccountModel(
            _user.value!!.name,
            date.toInt(),
            _user.value!!.achievements
        )
    }

    fun addAchievement(key: String, value: String) {
        coroutineScope.launch {
            repository.addAchievement(mapOf(key to value))
        }
        val list = _user.value!!.achievements.toMutableMap()
        list.put(key, value)
        _user.value = AccountModel(
            _user.value!!.name,
            _user.value!!.dayCount,
            list
        )
    }

}