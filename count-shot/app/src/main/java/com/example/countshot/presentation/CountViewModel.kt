package com.example.countshot.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CountViewModel:ViewModel(){
    private val _countUiState = MutableStateFlow(CountUiState())
    val countUiState: StateFlow<CountUiState> = _countUiState

    fun incrementCount(){
        val currentCount = _countUiState.value.count
        _countUiState.value = CountUiState(currentCount + 1)
    }
}

data class CountUiState(
    val count: Int = 0
)
