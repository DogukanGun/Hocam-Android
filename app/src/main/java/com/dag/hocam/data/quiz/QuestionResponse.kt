package com.dag.hocam.data.quiz

data class QuestionResponse(
    val id: String,
    var question: String = "",
    var correctAnswer: String,
    var questionUrl:String? = ""
)