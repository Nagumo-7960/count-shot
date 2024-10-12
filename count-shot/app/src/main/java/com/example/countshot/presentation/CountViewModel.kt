package com.example.countshot.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountViewModel @Inject constructor(
    private val countDao: CountDao
) : ViewModel() {
    private val _countUiState = MutableStateFlow(CountUiState())
    val countUiState: StateFlow<CountUiState> = _countUiState

    init {
        loadCount()
    }

    // データベースからカウントを読み込む
    private fun loadCount() {
        viewModelScope.launch {
            val entity = countDao.getCount()
            _countUiState.value = CountUiState(entity?.count ?: 0)
        }
    }

    // カウントを増やしてデータベースに保存
    fun incrementCount() {
        val currentCount = _countUiState.value.count + 1
        _countUiState.value = CountUiState(currentCount)

        viewModelScope.launch {
            countDao.insertCount(CountEntity(id = 0, count = currentCount))
        }
    }

    // カウントをリセットしてデータベースに保存
    fun resetCount() {
        _countUiState.value = CountUiState(0)

        viewModelScope.launch {
            countDao.insertCount(CountEntity(id = 0, count = 0))
        }
    }
}

data class CountUiState(
    val count: Int = 0
)
