package com.example.lokakuis.base.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class XmlHttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .build()
        )
    }
}
