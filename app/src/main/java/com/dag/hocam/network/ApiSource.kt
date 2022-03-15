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

interface ApiSource {

     fun addTopic(addTopicRequest: AddTopicRequest): Observable<TopicResponse>

     fun addSubject(addSubjectRequest: AddSubjectRequest): Observable<AddSubjectRequest>

     fun addQuiz(addQuizRequest: AddQuizRequest): Observable<AddQuizResponse>

     fun addQuestion(addQuestionRequest: AddQuestionRequest)

     fun getAllTopics(): Observable<List<GetAllTopicResponse>>

     fun getAllTopicsByName(name: String): Observable<GetAllTopicResponse>

     fun getAllQuizzes(): Observable<List<Quiz>>

     fun completeQuiz(completeQuizRequest: CompleteQuizRequest): Observable<CompleteQuizRequest>

     fun getQuizzesByName(quizName: String): Observable<List<QuestionResponse>>

     fun login(loginRequest: LoginRequest): Observable<AuthenticationResponse>

     fun register(registerRequest: RegisterRequest): Observable<AuthenticationResponse>

     fun getAllSubject(): Observable<List<AddSubjectRequest>>

     fun getAllSubjectByName(name: String): Observable<List<AddSubjectRequest>>

     fun getAllSubjectQuestionsBySubjectName(name: String): Observable<List<AddExampleQuestionRequest>>

}