package com.example.lokakuis.ui.quiz.detail

import com.example.lokakuis.base.architecture.AuthenticatedViewModel
import com.example.lokakuis.entity.response.quiz.Quiz
import com.example.lokakuis.entity.response.section.Section
import com.example.lokakuis.entity.response.topic.Topic

class QuizDetailViewModel(
    val topic: Topic,
    val section: Section,
    val quiz: Quiz
) : AuthenticatedViewModel() {
}