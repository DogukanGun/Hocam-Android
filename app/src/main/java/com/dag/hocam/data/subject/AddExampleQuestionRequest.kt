package com.dag.hocam.data.subject

data class AddExampleQuestionRequest (
    val question: String,
    val correctAnswer: String,
    val solution: String,
    val subjectID: Long
    )