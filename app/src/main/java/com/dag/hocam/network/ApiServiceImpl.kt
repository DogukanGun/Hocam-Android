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
import retrofit2.Retrofit
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(retrofit:Retrofit): ApiSource {
    private val apiService:ApiService = retrofit.create(ApiService::class.java)
    override fun addTopic(addTopicRequest: AddTopicRequest): TopicResponse {
        return apiService.addTopic(addTopicRequest)
    }

    override fun addSubject(addSubjectRequest: AddSubjectRequest): AddSubjectRequest {
        return apiService.addSubject(addSubjectRequest)
    }

    override fun addQuiz(addQuizRequest: AddQuizRequest) {
        apiService.addQuiz(addQuizRequest)
    }

    override fun addQuestion(addQuestionRequest: AddQuestionRequest) {
        apiService.addQuestion(addQuestionRequest)
    }

    override fun getAllTopics(): GetAllTopicResponse {
        return apiService.getAllTopics()
    }

    override fun getAllTopicsByName(name: String): GetAllTopicResponse {
        return apiService.getAllTopicsByName(name)
    }

    override fun getAllQuizzes(): Quiz {
        return apiService.getAllQuizzes()
    }

    override fun completeQuiz(completeQuizRequest: CompleteQuizRequest): CompleteQuizRequest {
        return apiService.completeQuiz(completeQuizRequest)
    }

    override fun getQuizzesByName(quizName: String): List<Quiz> {
        return apiService.getQuizzesByName(quizName)
    }

    override fun login(loginRequest: LoginRequest): AuthenticationResponse {
        return apiService.login(loginRequest)
    }

    override fun register(registerRequest: RegisterRequest): AuthenticationResponse {
        return apiService.register(registerRequest)
    }

    override fun getAllSubject(): List<AddSubjectRequest> {
        return apiService.getAllSubject()
    }

    override fun getAllSubjectByName(name: String): List<AddSubjectRequest> {
        return apiService.getAllSubjectByName(name)
    }

    override fun getAllSubjectQuestionsBySubjectName(name: String): List<AddExampleQuestionRequest> {
        return apiService.getAllSubjectQuestionsBySubjectName(name)
    }

}