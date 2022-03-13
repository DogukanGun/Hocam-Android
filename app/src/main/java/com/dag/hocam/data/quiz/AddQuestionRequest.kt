package com.dag.hocam.data.quiz


data class AddQuestionRequest (
    val question: String,
    val correctAnswer: String,
    val quizID: Long
)