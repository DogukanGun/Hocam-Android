package com.dag.hocam.data.quiz

data class AddQuizRequest(
    val quizName: String,
    val topicID: Int,
    var createQuestionRequests: List<AddQuestionRequest>
)
