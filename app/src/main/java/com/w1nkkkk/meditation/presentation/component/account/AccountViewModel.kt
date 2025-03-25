package com.w1nkkkk.meditation.presentation.component.account

import androidx.lifecycle.ViewModel
import com.w1nkkkk.meditation.domain.account.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repository: AccountRepository
) : ViewModel() {

}