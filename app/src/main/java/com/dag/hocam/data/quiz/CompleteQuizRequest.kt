package com.dag.hocam.data.quiz

data class CompleteQuizRequest (
    val username: String,
    val quizId: Int,
    var point:Int
    )