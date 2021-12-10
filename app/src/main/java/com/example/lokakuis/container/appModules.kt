package com.example.lokakuis.container

import com.example.lokakuis.entity.response.quiz.Quiz
import com.example.lokakuis.entity.response.section.Section
import com.example.lokakuis.entity.response.topic.Topic
import com.example.lokakuis.ui.home.feed.FeedViewModel
import com.example.lokakuis.ui.home.profile.ProfileViewModel
import com.example.lokakuis.ui.home.profile.edit.EditProfileViewModel
import com.example.lokakuis.ui.login.LoginViewModel
import com.example.lokakuis.ui.quiz.QuizViewModel
import com.example.lokakuis.ui.quiz.detail.QuizDetailViewModel
import com.example.lokakuis.ui.register.RegisterViewModel
import com.example.lokakuis.ui.section.SectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { LoginViewModel() }
    viewModel { RegisterViewModel() }
    viewModel { FeedViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { EditProfileViewModel() }
    viewModel { (topic: Topic) -> SectionViewModel(topic) }
    viewModel { (topic: Topic, section: Section) -> QuizViewModel(topic, section) }
    viewModel { (topic: Topic, section: Section, quiz: Quiz) -> QuizDetailViewModel(topic, section, quiz) }
}