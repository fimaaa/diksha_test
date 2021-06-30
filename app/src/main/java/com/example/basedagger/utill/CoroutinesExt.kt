package com.example.basedagger.utill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun <T> ViewModel.safeApiCall(
    call: suspend () -> T,
    error: (Throwable) -> Unit = {}
) {
    viewModelScope.launch {
        try {
            call()
        } catch (t: Throwable) {
            error(t)
        }
    }
}

fun <T> CoroutineScope.safeApiCall(
    call: suspend () -> T,
    error: (Throwable) -> Unit = {}
) {
    launch {
        try {
            call()
        } catch (t: Throwable) {
            error(t)
        }
    }
}

fun launchOn(coroutineContext: CoroutineContext, call: CoroutineScope.() -> (Unit)) {
    GlobalScope.launch(coroutineContext) {
        call(this)
    }
}