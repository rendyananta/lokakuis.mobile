package com.example.lokakuis.container

import com.example.lokakuis.ui.home.feed.FeedViewModel
import com.example.lokakuis.ui.login.LoginViewModel
import com.example.lokakuis.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { LoginViewModel() }
    viewModel { RegisterViewModel() }
    viewModel { FeedViewModel() }
}