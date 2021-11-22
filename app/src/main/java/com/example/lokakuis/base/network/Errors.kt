package com.example.lokakuis.base.network

import android.util.Log
import com.example.lokakuis.base.architecture.BaseViewModel
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

object Errors {
    fun handle(from: BaseViewModel, exception: Exception, errorHandler: ErrorHandler = ErrorHandler(from, exception)) {
        when (exception) {
            is HttpException -> {
                when (exception.response()?.code()) {
                    401 -> errorHandler.handle401()
                    404 -> errorHandler.handle404()
                    422 -> errorHandler.handle422()
                    500 -> errorHandler.handle500()
                    502 -> errorHandler.handle502()
                    else -> errorHandler.default(exception)
                }
            }
            else -> errorHandler.default(exception)
        }
    }

    open class ErrorHandler(private val vm: BaseViewModel?, e: Exception) {

        private var message: String = ""

        init {
            if (e is HttpException) {
                message = e.response()?.errorBody()?.string()?.let {
                    try {
                        val res = JSONObject(it)
                        val string = res.getString("message")
                        string
                    } catch (e: JSONException) {
                        e.message.toString()
                    }
                } ?: run {
                    e.message().toString()
                }
            }
        }

        open fun handle401() {
            vm?.message?.value = BaseViewModel.Message.ErrorMessage(message)
        }

        open fun handle404() {
            vm?.message?.value = BaseViewModel.Message.ErrorMessage(message)
        }

        open fun handle422() {
            vm?.message?.value = BaseViewModel.Message.ErrorMessage(message)
        }

        open fun handle500() {
            vm?.message?.value = BaseViewModel.Message.ErrorMessage("Internal Server Error")
        }

        open fun handle502() {
            vm?.message?.value = BaseViewModel.Message.ErrorMessage("Bad Gateway")
        }

        open fun default(e: Exception) {
            Log.d("Exception", e.toString())
            vm?.message?.value = BaseViewModel.Message.ErrorMessage(e.message.toString())
        }
    }
}
