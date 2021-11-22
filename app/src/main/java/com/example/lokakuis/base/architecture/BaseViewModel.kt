package com.example.lokakuis.base.architecture

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

open class BaseViewModel : ViewModel(), KoinComponent {

    val message: MutableLiveData<Message> = MutableLiveData()

    inline fun onError(lifecycleOwner: LifecycleOwner, crossinline block: (message: Message) -> Unit) {
        message.observe(lifecycleOwner, {
            block(it)
        })
    }

    fun alert(message: Message) {
        this.message.value = message
    }

    sealed class Message (val body: String) {
        data class SuccessMessage(val message: String) : Message(message)
        data class ErrorMessage(val message: String) : Message(message)
        data class WarningMessage(val message: String) : Message(message)
        data class InfoMessage(val message: String) : Message(message)
    }
}
