package com.dag.hocam.network

import com.dag.hocam.data.questionfromuser.AddQuestionFromUserRequest
import com.dag.hocam.data.questionfromuser.AddQuestionFromUserResponse
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

    override  fun addQuestion(addQuestionRequest: List<AddQuestionRequest>): Observable<List<AddQuestionRequest>> {
        return apiService.addQuestion(addQuestionRequest)
    }

    override  fun getAllTopics(): Observable<List<GetAllTopicResponse>> {
        return apiService.getAllTopics()
    }

    override  fun getAllTopicsByName(name: String): Observable<GetAllTopicResponse> {
        return apiService.getAllTopicsByName(name)
    }

    override  fun getAllQuizzes(getQuizRequest: GetQuizRequest): Observable<List<Quiz>> {
        return apiService.getAllQuizzes(getQuizRequest)
    }

    override  fun completeQuiz(completeQuizRequest: CompleteQuizRequest): Observable<CompleteQuizRequest> {
        return apiService.completeQuiz(completeQuizRequest)
    }

    override fun completeQuestion(completeQuestionRequest: CompleteQuestionRequest): Observable<List<CompleteQuestionResponse>> {
        return apiService.completeQuestion(completeQuestionRequest)
    }

    override  fun getQuizzesByName(getQuestionByQuiz: GetQuestionByQuiz): Observable<List<QuestionResponse>> {
        return apiService.getQuizzesByName(getQuestionByQuiz)
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

    override fun solveQuestion(questionFromUserRequest: AddQuestionFromUserRequest): Observable<AddQuestionFromUserRequest> {
        return apiService.solveQuestion(questionFromUserRequest)
    }

    override fun getQuestionFromUser(questionId: Int): Observable<AddQuestionFromUserResponse> {
        return apiService.getQuestionFromUser(questionId)
    }

    override fun getAllQuestionFromUser(): Observable<List<AddQuestionFromUserResponse>> {
        return apiService.getAllQuestionFromUser()
    }

    override fun addQuestionFromUser(questionFromUserRequest: AddQuestionFromUserRequest): Observable<AddQuestionFromUserRequest> {
        return apiService.addQuestionFromUser(questionFromUserRequest)
    }

    override fun getAllSolvedQuizzes(username: String): Observable<List<SolvedQuizResponse>> {
        return apiService.getAllSolvedQuizzes(username)
    }

    override fun getAllQuizzesByTopic(getQuizRequest: GetQuizRequest): Observable<List<Quiz>> {
        return apiService.getAllQuizzesByTopic(getQuizRequest)
    }

}