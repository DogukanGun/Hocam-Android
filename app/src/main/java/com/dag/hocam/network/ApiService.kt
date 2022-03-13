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
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("add/topic")
    fun addTopic(@Body addTopicRequest: AddTopicRequest): TopicResponse

    @POST("add/subject")
    fun addSubject(@Body addSubjectRequest: AddSubjectRequest): AddSubjectRequest

    @POST("add/quiz")
    fun addQuiz(@Body addQuizRequest: AddQuizRequest)

    @POST("add/question")
    fun addQuestion(@Body addQuestionRequest: AddQuestionRequest)

    @POST("topic/all")
    fun getAllTopics(): GetAllTopicResponse

    @POST("topic/all/{name}")
    fun getAllTopicsByName(@Path("name") name: String): GetAllTopicResponse

    @POST("quiz/getAll")
    fun getAllQuizzes(): Quiz

    @POST("quiz/question/complete/quiz")
    fun completeQuiz(completeQuizRequest: CompleteQuizRequest): CompleteQuizRequest

    @POST("quiz/question/getAll/{quizName}")
    fun getQuizzesByName(@Path("quizName") quizName: String): List<Quiz>

    @POST("auth/login")
    fun login(@Body loginRequest: LoginRequest): AuthenticationResponse

    @POST("auth/register")
    fun register(@Body registerRequest: RegisterRequest): AuthenticationResponse

    @POST("subject/subject/all")
    fun getAllSubject(): List<AddSubjectRequest>

    @POST("subject/subjectbyname/{subjectName}")
    fun getAllSubjectByName(@Path("subjectName") name: String): List<AddSubjectRequest>

    @POST("subject/question/{subjectName}")
    fun getAllSubjectQuestionsBySubjectName(@Path("subjectName") name: String): List<AddExampleQuestionRequest>

}