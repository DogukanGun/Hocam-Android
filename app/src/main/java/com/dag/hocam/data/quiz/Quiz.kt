package com.dag.hocam.data.quiz

data class Quiz(
    val id: Long,
    val quizName: String,
    val questions: List<QuestionResponse>
)
