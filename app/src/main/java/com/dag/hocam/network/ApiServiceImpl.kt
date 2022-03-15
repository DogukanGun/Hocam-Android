package com.dag.hocam.network

import com.dag.hocam.data.quiz.*
import com.dag.hocam.data.subject.AddExampleQuestionRequest
import com.dag.hocam.data.subject.AddSubjectRequest
import com.dag.hocam.data.topic.AddTopicRequest
import com.dag.hocam.data.topic.GetAllTopicResponse
import com.dag.hocam.data.topic.TopicResponse
import com.dag.hocam.data.user.AuthenticationResponse
import com.dag.hocam.data.user.LoginRequest
import com.dag.hocam.data.user.RegisterRequest
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(retrofit:Retrofit): ApiSource {
    private val apiService:ApiService = retrofit.create(ApiService::class.java)
    override   fun addTopic(addTopicRequest: AddTopicRequest): Observable<TopicResponse> {
        return apiService.addTopic(addTopicRequest)
    }

    override  fun addSubject(addSubjectRequest: AddSubjectRequest): Observable<AddSubjectRequest> {
        return apiService.addSubject(addSubjectRequest)
    }

    override  fun addQuiz(addQuizRequest: AddQuizRequest): Observable<AddQuizResponse> {
        return apiService.addQuiz(addQuizRequest)
    }

    override  fun addQuestion(addQuestionRequest: AddQuestionRequest) {
        apiService.addQuestion(addQuestionRequest)
    }

    override  fun getAllTopics(): Observable<List<GetAllTopicResponse>> {
        return apiService.getAllTopics()
    }

    override  fun getAllTopicsByName(name: String): Observable<GetAllTopicResponse> {
        return apiService.getAllTopicsByName(name)
    }

    override  fun getAllQuizzes(): Observable<List<Quiz>> {
        return apiService.getAllQuizzes()
    }

    override  fun completeQuiz(completeQuizRequest: CompleteQuizRequest): Observable<CompleteQuizRequest> {
        return apiService.completeQuiz(completeQuizRequest)
    }

    override  fun getQuizzesByName(quizName: String): Observable<List<QuestionResponse>> {
        return apiService.getQuizzesByName(quizName)
    }

    override  fun login(loginRequest: LoginRequest): Observable<AuthenticationResponse> {
        return apiService.login(loginRequest)
    }

    override  fun register(registerRequest: RegisterRequest): Observable<AuthenticationResponse> {
        return apiService.register(registerRequest)
    }

    override  fun getAllSubject(): Observable<List<AddSubjectRequest>> {
        return apiService.getAllSubject()
    }

    override  fun getAllSubjectByName(name: String): Observable<List<AddSubjectRequest>> {
        return apiService.getAllSubjectByName(name)
    }

    override  fun getAllSubjectQuestionsBySubjectName(name: String): Observable<List<AddExampleQuestionRequest>> {
        return apiService.getAllSubjectQuestionsBySubjectName(name)
    }

}