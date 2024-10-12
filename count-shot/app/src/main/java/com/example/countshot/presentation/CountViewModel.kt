package com.example.countshot.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CountViewModel @Inject constructor() : ViewModel() {
    private val _countUiState = MutableStateFlow(CountUiState())
    val countUiState: StateFlow<CountUiState> = _countUiState

    fun incrementCount() {
        val currentCount = _countUiState.value.count
        _countUiState.value = CountUiState(currentCount + 1)
    }
}

data class CountUiState(
    val count: Int = 0
)
