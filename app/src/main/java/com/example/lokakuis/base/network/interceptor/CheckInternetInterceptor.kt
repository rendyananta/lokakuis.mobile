package com.example.lokakuis.base.network.interceptor

import android.content.Context
import com.example.lokakuis.base.extensions.isNetworkConnected
import com.example.lokakuis.base.network.exception.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class CheckInternetInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (! context.isNetworkConnected()) {
            throw NoConnectivityException()
        }

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

}
