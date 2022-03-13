package com.dag.hocam.network

import com.dag.hocam.data.quiz.AddQuestionRequest
import com.dag.hocam.data.quiz.AddQuizRequest
import com.dag.hocam.data.quiz.CompleteQuizRequest
import com.dag.hocam.data.quiz.Quiz
import com.dag.hocam.data.subject.AddExampleQuestionRequest
import com.dag.hocam.data.subject.AddSubjectRequest
import com.dag.hocam.data.topic.AddTopicRequest
import com.dag.hocam.data.topic.GetAllTopicResponse
import com.dag.hocam.data.topic.TopicResponse
import com.dag.hocam.data.user.AuthenticationResponse
import com.dag.hocam.data.user.LoginRequest
import com.dag.hocam.data.user.RegisterRequest

interface ApiSource {

    fun addTopic(addTopicRequest: AddTopicRequest): TopicResponse

    fun addSubject(addSubjectRequest: AddSubjectRequest): AddSubjectRequest

    fun addQuiz(addQuizRequest: AddQuizRequest)

    fun addQuestion(addQuestionRequest: AddQuestionRequest)

    fun getAllTopics(): GetAllTopicResponse

    fun getAllTopicsByName(name: String): GetAllTopicResponse

    fun getAllQuizzes(): Quiz

    fun completeQuiz(completeQuizRequest: CompleteQuizRequest): CompleteQuizRequest

    fun getQuizzesByName(quizName: String): List<Quiz>

    fun login(loginRequest: LoginRequest): AuthenticationResponse

    fun register(registerRequest: RegisterRequest): AuthenticationResponse

    fun getAllSubject(): List<AddSubjectRequest>

    fun getAllSubjectByName(name: String): List<AddSubjectRequest>

    fun getAllSubjectQuestionsBySubjectName(name: String): List<AddExampleQuestionRequest>

}