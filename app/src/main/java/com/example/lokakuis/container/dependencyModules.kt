package com.example.lokakuis.container

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.lokakuis.BuildConfig
import com.example.lokakuis.base.network.interceptor.AuthInterceptor
import com.example.lokakuis.base.network.interceptor.CheckInternetInterceptor
import com.example.lokakuis.base.network.interceptor.HttpErrorInterceptor
import com.example.lokakuis.base.network.interceptor.XmlHttpRequestInterceptor
import com.example.lokakuis.http.request.AuthApi
import com.example.lokakuis.http.request.QuizApi
import com.example.lokakuis.http.request.SectionApi
import com.example.lokakuis.http.request.TopicApi
import com.google.gson.GsonBuilder
import dev.poteto.formvalidator.Validator
import dev.poteto.formvalidator.messages.Indonesian
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dependencyModules = module {

    single {
        PreferenceManager.getDefaultSharedPreferences(androidContext())
    }
    single { GsonBuilder().setLenient().create() }
    single { GsonConverterFactory.create(get()) }
    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
    single { XmlHttpRequestInterceptor() }
    single { CheckInternetInterceptor(androidContext()) }
    single { HttpErrorInterceptor() }
    single { AuthInterceptor() }

    single(named("guest_client")) {
        OkHttpClient.Builder()
            .addInterceptor(get<CheckInternetInterceptor>())
            .addInterceptor(get<XmlHttpRequestInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single(named("authenticated_client")) {
        OkHttpClient.Builder()
            .addInterceptor(get<CheckInternetInterceptor>())
            .addInterceptor(get<XmlHttpRequestInterceptor>())
            .addInterceptor(get<AuthInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single (named("guest")) {
        Retrofit.Builder()
            .addConverterFactory(get<GsonConverterFactory>())
            .baseUrl(BuildConfig.BASE_URL)
            .client(get(named("guest_client")))
            .build()
    }

    single (named("authenticated")) {
        Retrofit.Builder()
            .addConverterFactory(get<GsonConverterFactory>())
            .baseUrl(BuildConfig.BASE_URL)
            .client(get(named("authenticated_client")))
            .build()
    }

    single { Validator(Indonesian) }

    single { get<Retrofit>(named("guest")).create(AuthApi::class.java) as AuthApi }
    single { get<Retrofit>(named("authenticated")).create(TopicApi::class.java) as TopicApi }
    single { get<Retrofit>(named("authenticated")).create(SectionApi::class.java) as SectionApi }
    single { get<Retrofit>(named("authenticated")).create(QuizApi::class.java) as QuizApi }
}