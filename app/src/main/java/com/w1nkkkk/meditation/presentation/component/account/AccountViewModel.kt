package com.w1nkkkk.meditation.presentation.component.account

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.w1nkkkk.meditation.domain.account.AccountModel
import com.w1nkkkk.meditation.domain.account.AccountRepository
import com.w1nkkkk.meditation.presentation.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repository: AccountRepository,
) : ViewModel() {

    val user : MutableStateFlow<AccountModel> = MutableStateFlow(AccountModel())

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        if (FirebaseAuth.getInstance().uid != null) {
            getAccountData(FirebaseAuth.getInstance().uid!!)
        }
    }

    fun getAccountData(id: String) {
        coroutineScope.launch {
            user.emit(repository.getAccountData(id))
        }
    }

    fun changeName(name : String) {
        coroutineScope.launch {
            repository.changeName(name)
        }
        user.value = user.value.copy(name = name)
    }

    fun changeDaysCount(date: Long) {
        if (MainActivity.preferences.value.updateDaysCount) {
            coroutineScope.launch {
                repository.changeDaysCount(date)
            }
            user.value = user.value.copy(dayCount = date.toInt())
        }
    }

    fun changeDaysCount() {
        if (MainActivity.preferences.value.updateDaysCount) {
            coroutineScope.launch {
                repository.changeDaysCount((user.value.dayCount + 1).toLong())
            }
            user.value = user.value.copy(dayCount = user.value.dayCount + 1)
        }
    }

    fun addAchievement(key: String, value: String) {
        coroutineScope.launch {
            repository.addAchievement(mapOf(key to value))
        }
        val list = user.value.achievements.toMutableMap()
        list.put(key, value)
        user.value = AccountModel(
            user.value.name,
            user.value.dayCount,
            list,
            user.value.emotion_state
        )
    }

    fun changeEmotionalState(state: Int) {
        coroutineScope.launch {
            repository.changeEmotionalState(state)
        }
        user.value = user.value.copy(emotion_state = state)
    }
}