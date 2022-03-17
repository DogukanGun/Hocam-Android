package com.dag.hocam.data.quiz

data class AddQuizRequest(
    val quizName: String,
    val topicId: Int,
    var createQuestionRequests: List<AddQuestionRequest>
)
