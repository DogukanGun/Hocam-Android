package com.dag.hocam.data.quiz

data class GetQuestionByQuiz (
    var quizName: String,
    var maxQuestionNumber: Int,
    var questionFiltered: Boolean
        )