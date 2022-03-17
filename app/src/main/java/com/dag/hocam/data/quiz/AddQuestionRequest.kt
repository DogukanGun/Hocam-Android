package com.dag.hocam.data.quiz


data class AddQuestionRequest (
    var question: String = "",
    var correctAnswer: String = "A",
    val quizID: Long,
)