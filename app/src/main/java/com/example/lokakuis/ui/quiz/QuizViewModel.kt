package com.example.lokakuis.ui.quiz

import com.example.lokakuis.base.architecture.AuthenticatedViewModel
import com.example.lokakuis.entity.response.section.Section
import com.example.lokakuis.entity.response.topic.Topic

class QuizViewModel(private val topic: Topic, private val section: Section) : AuthenticatedViewModel() {
}