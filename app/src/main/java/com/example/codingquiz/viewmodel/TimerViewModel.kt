package com.example.codingquiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class TimerViewModel(
    private val timeout: Duration = 10.seconds,
) : ViewModel() {
    private var timer: Job? = null

    private val _timeLeft = MutableStateFlow(timeout.inWholeSeconds)
    val timeLeft get() = _timeLeft.asStateFlow()

    fun start() {
        timer?.cancel()
        timer = viewModelScope.launch {
            for (time in (timeout.inWholeSeconds downTo 0)) {
                _timeLeft.value = time
                delay(1.seconds)
            }
        }
    }

    fun clear() {
        timer?.cancel()
        timer = null
        _timeLeft.value = timeout.inWholeSeconds
    }
}