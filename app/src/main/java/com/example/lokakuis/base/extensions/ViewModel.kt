package com.example.lokakuis.base.extensions

import androidx.lifecycle.viewModelScope
import com.example.lokakuis.base.architecture.BaseViewModel
import com.example.lokakuis.base.network.Errors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

inline fun BaseViewModel.safeCall(crossinline body: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch {
        try {
            body.invoke(this)
        } catch (e: Exception) {
            Errors.handle(this@safeCall, e)
        }
    }
}