package com.example.codingquiz.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object Timer {
    private val scope = CoroutineScope(Dispatchers.IO)
    private var timer: Job? = null

    private val _timeLeft = MutableStateFlow<Long>(10)
    val timeLeft get() = _timeLeft.asStateFlow()

    fun start(timeout: Duration) {
        timer = scope.launch {
            for (time in (timeout.inWholeSeconds downTo 0)) {
                _timeLeft.value = time
                delay(1.seconds)
            }
        }
    }

    fun stop() {
        timer?.cancel()
        timer = null
    }
}