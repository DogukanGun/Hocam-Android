package com.dag.hocam.data.subject

data class AddSubjectRequest(
    val subjectName: String,
    val subjectVideoURL: String,
    val exampleQuestionRequests: List<AddExampleQuestionRequest>
)