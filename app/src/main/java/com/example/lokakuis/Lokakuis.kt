package com.example.lokakuis

import android.app.Application
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.example.lokakuis.container.appModules
import com.example.lokakuis.container.dependencyModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Lokakuis : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Lokakuis)
            modules(listOf(dependencyModules, appModules))
        }
    }

    @GlideModule
    inner class Glide : AppGlideModule()

}