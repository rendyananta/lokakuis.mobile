package com.example.lokakuis.base.network.interceptor

import com.example.lokakuis.base.network.exception.HttpErrorException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HttpErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val response = chain.proceed(originalRequest)

        return if (response.code >= 400) {
            throw HttpErrorException(response)
        } else {
            response
        }
    }
}
