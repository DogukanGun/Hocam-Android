package com.dag.hocam.network

import com.dag.hocam.application.Constant
import com.dag.hocam.data.questionfromuser.AddQuestionFromUserRequest
import com.dag.hocam.data.questionfromuser.AddQuestionFromUserResponse
import com.dag.hocam.data.questionfromuser.SolveQuestionResponse
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
import retrofit2.http.*

interface ApiService {

    @POST("add/topic")
    fun addTopic(@Body addTopicRequest: AddTopicRequest): Observable<TopicResponse>

    @POST("add/subject")
    fun addSubject(@Body addSubjectRequest: AddSubjectRequest): Observable<AddSubjectRequest>

    @POST("add/quiz")
    fun addQuiz(@Body addQuizRequest: AddQuizRequest): Observable<AddQuizResponse>

    @POST("add/question")
    fun addQuestion(@Body addQuestionRequest: List<AddQuestionRequest>): Observable<List<AddQuestionRequest>>

    @POST("questionfromuser/add")
    fun addQuestionFromUser(@Body questionFromUserRequest: AddQuestionFromUserRequest):
            Observable<AddQuestionFromUserRequest>

    @POST("topic/all")
    fun getAllTopics(): Observable<List<GetAllTopicResponse>>

    @POST("topic/all/{name}")
    fun getAllTopicsByName(@Path("name") name: String): Observable<GetAllTopicResponse>

    @POST("quiz/get/all")
    fun getAllQuizzes(@Body getQuizRequest: GetQuizRequest): Observable<List<Quiz>>

    @POST("quiz/quiz/getby/topic")
    fun getAllQuizzesByTopic(@Body getQuizRequest: GetQuizRequest): Observable<List<Quiz>>

    @POST("quiz/question/complete/quiz")
    fun completeQuiz(@Body completeQuizRequest: CompleteQuizRequest): Observable<CompleteQuizRequest>

    @POST("quiz/question/complete/question")
    fun completeQuestion(@Body completeQuestionRequest: CompleteQuestionRequest): Observable<List<CompleteQuestionResponse>>

    @POST("quiz/question/getAll")
    fun getQuizzesByName(@Body getQuestionByQuiz: GetQuestionByQuiz): Observable<List<QuestionResponse>>

    @POST("auth/login")
    fun login(@Body loginRequest: LoginRequest): Observable<AuthenticationResponse>

    @POST("auth/register")
    fun register(@Body registerRequest: RegisterRequest): Observable<AuthenticationResponse>

    @POST("subject/subject/all")
    fun getAllSubject(): Observable<List<AddSubjectRequest>>

    @POST("subject/subjectbyname/{subjectName}")
    fun getAllSubjectByName(@Path("subjectName") name: String): Observable<List<AddSubjectRequest>>

    @POST("subject/question/{subjectName}")
    fun getAllSubjectQuestionsBySubjectName(@Path("subjectName") name: String):
            Observable<List<AddExampleQuestionRequest>>

    @POST("questionfromuser/solve/question")
    fun solveQuestion(@Body questionFromUserRequest: AddQuestionFromUserRequest):
            Observable<AddQuestionFromUserRequest>

    @POST("questionfromuser/get/id/{questionId}")
    fun getQuestionFromUser(@Path("questionId") questionId: Int):
            Observable<AddQuestionFromUserResponse>

    @POST("questionfromuser/get/all")
    fun getAllQuestionFromUser():
            Observable<List<AddQuestionFromUserResponse>>

    @POST("quiz/quiz/solved/{username}")
    fun getAllSolvedQuizzes(@Path("username") username: String):
            Observable<List<SolvedQuizResponse>>
}