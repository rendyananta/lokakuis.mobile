package com.example.lokakuis.base.architecture

import android.os.Parcelable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.parcelize.Parcelize
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
        @Parcelize data class SuccessMessage(val message: String) : Message(message), Parcelable
        @Parcelize data class ErrorMessage(val message: String) : Message(message), Parcelable
        @Parcelize data class WarningMessage(val message: String) : Message(message), Parcelable
        @Parcelize data class InfoMessage(val message: String) : Message(message), Parcelable
    }
}
