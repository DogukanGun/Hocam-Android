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
import retrofit2.http.Body
import retrofit2.http.Path

interface ApiSource {

     fun addTopic(addTopicRequest: AddTopicRequest): Observable<TopicResponse>

     fun addSubject(addSubjectRequest: AddSubjectRequest): Observable<AddSubjectRequest>

     fun addQuiz(addQuizRequest: AddQuizRequest): Observable<AddQuizResponse>

     fun addQuestion(addQuestionRequest: List<AddQuestionRequest>): Observable<List<AddQuestionRequest>>

     fun getAllTopics(): Observable<List<GetAllTopicResponse>>

     fun getAllTopicsByName(name: String): Observable<GetAllTopicResponse>

     fun getAllQuizzes(getQuizRequest: GetQuizRequest): Observable<List<Quiz>>

     fun completeQuiz(completeQuizRequest: CompleteQuizRequest): Observable<CompleteQuizRequest>

     fun getQuizzesByName(getQuestionByQuiz: GetQuestionByQuiz): Observable<List<QuestionResponse>>

     fun login(loginRequest: LoginRequest): Observable<AuthenticationResponse>

     fun register(registerRequest: RegisterRequest): Observable<AuthenticationResponse>

     fun getAllSubject(): Observable<List<AddSubjectRequest>>

     fun getAllSubjectByName(name: String): Observable<List<AddSubjectRequest>>

     fun getAllSubjectQuestionsBySubjectName(name: String): Observable<List<AddExampleQuestionRequest>>

     fun solveQuestion(questionFromUserRequest: AddQuestionFromUserRequest):
             Observable<AddQuestionFromUserRequest>

     fun getQuestionFromUser(questionId: Int):
             Observable<AddQuestionFromUserResponse>

     fun getAllQuestionFromUser():
             Observable<List<AddQuestionFromUserResponse>>

     fun addQuestionFromUser(questionFromUserRequest: AddQuestionFromUserRequest):
             Observable<AddQuestionFromUserRequest>

     fun getAllSolvedQuizzes(username: String):
             Observable<List<SolvedQuizResponse>>

     fun getAllQuizzesByTopic( getQuizRequest: GetQuizRequest): Observable<List<Quiz>>
}