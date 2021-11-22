package com.example.lokakuis.base.extensions

import androidx.lifecycle.viewModelScope
import com.example.lokakuis.base.architecture.BaseViewModel
import com.example.lokakuis.base.network.Errors
import kotlinx.coroutines.launch

inline fun BaseViewModel.safeCall(crossinline body: () -> Unit) {
    viewModelScope.launch {
        try {
            body.invoke()
        } catch (e: Exception) {
            Errors.handle(this@safeCall, e)
        }
    }
}