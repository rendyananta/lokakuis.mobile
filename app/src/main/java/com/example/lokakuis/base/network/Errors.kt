package com.example.lokakuis.base.network

import android.util.Log
import com.example.lokakuis.base.architecture.BaseViewModel
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

object Errors {
    fun handle(from: BaseViewModel, throwable: Throwable, errorHandler: ErrorHandler = ErrorHandler(from, throwable)) {
        when (throwable) {
            is HttpException -> {
                when (throwable.response()?.code()) {
                    401 -> errorHandler.handle401()
                    404 -> errorHandler.handle404()
                    422 -> errorHandler.handle422()
                    500 -> errorHandler.handle500()
                    502 -> errorHandler.handle502()
                    503 -> errorHandler.handle503()
                    else -> errorHandler.default(throwable)
                }
            }
            else -> errorHandler.default(throwable)
        }
    }

    open class ErrorHandler(private val vm: BaseViewModel?, e: Throwable) {

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
            vm?.alert(BaseViewModel.Message.ErrorMessage(message))
        }

        open fun handle404() {
            vm?.alert(BaseViewModel.Message.ErrorMessage(message))
        }

        open fun handle422() {
            vm?.alert(BaseViewModel.Message.ErrorMessage(message))
        }

        open fun handle500() {
            vm?.alert(BaseViewModel.Message.ErrorMessage("Internal Server Error"))
        }

        open fun handle502() {
            vm?.alert(BaseViewModel.Message.ErrorMessage("Bad Gateway"))
        }

        open fun handle503() {
            vm?.alert(BaseViewModel.Message.ErrorMessage("System On Maintenance"))
        }

        open fun default(e: Throwable) {
            Log.d("Exception", e.toString())
            vm?.alert(BaseViewModel.Message.ErrorMessage(e.message.toString()))
        }
    }
}
