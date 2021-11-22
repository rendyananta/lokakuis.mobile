package com.example.lokakuis.base.network.exception

import com.example.lokakuis.base.extensions.fromJson
import com.example.lokakuis.entity.response.Response
import com.google.gson.Gson
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import okhttp3.Response as OkHttpResponse
import java.io.IOException

class HttpErrorException(val response: OkHttpResponse) : IOException(), KoinComponent {

    private val gson: Gson by inject()

    override val message: String = response.body?.let {
        val json = gson.fromJson<Response<String, String>>(it.string())
        json.message
    } ?: "Kesalahan Server"

}
