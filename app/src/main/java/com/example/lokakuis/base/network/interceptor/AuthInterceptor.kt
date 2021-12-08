package com.example.lokakuis.base.network.interceptor

import com.example.lokakuis.base.extensions.dispatchNow
import com.example.lokakuis.service.auth.GetAuthorizationToken
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.component.KoinComponent

class AuthInterceptor : Interceptor, KoinComponent {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = dispatchNow(GetAuthorizationToken())

        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        )
    }
}
