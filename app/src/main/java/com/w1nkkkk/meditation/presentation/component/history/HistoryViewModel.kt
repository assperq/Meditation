package com.w1nkkkk.meditation.presentation.component.history

import com.w1nkkkk.meditation.domain.history.HistoryModel
import com.w1nkkkk.meditation.domain.history.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: HistoryRepository
) {
    val historyList : MutableStateFlow<List<HistoryModel>> = MutableStateFlow(emptyList())

    init {
        getHistoryList()
    }

    fun getHistoryList() {
        CoroutineScope(Dispatchers.IO).launch {
            historyList.emit(repository.getHistoryList())
        }
    }

    fun addHistoryItem(item : HistoryModel) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addHistoryItem(item)
            getHistoryList()
        }
    }

}