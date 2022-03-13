package com.dag.hocam.data.topic

import com.dag.hocam.data.quiz.Quiz

data class GetAllTopicResponse(
    val id: Long,
    val topicName: String,
    val quizzes: List<Quiz>
)
