package com.dag.hocam.ui.admin

import com.dag.hocam.application.HocamVM
import com.dag.hocam.data.quiz.*
import com.dag.hocam.data.topic.AddTopicRequest
import com.dag.hocam.data.topic.GetAllTopicResponse
import com.dag.hocam.data.topic.TopicResponse
import com.dag.hocam.network.ApiSource
import com.dag.hocam.ui.admin.question.AdminAddQuestionFragmentVS
import com.dag.hocam.ui.admin.quiz.AdminAddQuizFragmentVS
import com.dag.hocam.ui.admin.topic.AddTopicVS
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AdminFragmentVM @Inject constructor(
    val apiSource: ApiSource
    ): HocamVM() {

    fun getTopics(){
        apiSource.getAllTopics()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<List<GetAllTopicResponse>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<GetAllTopicResponse>) {
                    val topicResponseList = mutableListOf<TopicResponse>()
                    for (index in t){
                        val topicResponse = TopicResponse(index.topicName,index.id.toInt())
                        topicResponseList.add(topicResponse)
                    }
                    state.postValue(AdminFragmentVS.SetTopic(topicResponseList))
                }

                override fun onError(e: Throwable) {
                    state.postValue(AdminAddQuestionFragmentVS.Error)
                }

                override fun onComplete() {
                }

            })
    }

    fun getAllQuizzes(getQuizRequest: GetQuizRequest){
        apiSource.getAllQuizzes(getQuizRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object: Observer<List<Quiz>>{
                override fun onSubscribe(d: Disposable) { }
                override fun onNext(t: List<Quiz>) {
                    state.postValue(AdminAddQuestionFragmentVS.SetQuiz(t))
                }
                override fun onError(e: Throwable) {
                    state.postValue(AdminAddQuestionFragmentVS.Error)
                }
                override fun onComplete() {}
            })
    }

    fun addQuestion(addQuizRequest: List<AddQuestionRequest>){
        apiSource.addQuestion(addQuizRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<List<AddQuestionRequest>>{
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<AddQuestionRequest>) {
                    state.postValue(AdminAddQuestionFragmentVS.QuestionAdded)
                }

                override fun onError(e: Throwable) {
                    state.postValue(AdminAddQuestionFragmentVS.Error)
                }

                override fun onComplete() {
                }

            })
    }

    fun addQuiz(addQuizRequest: AddQuizRequest){
        apiSource.addQuiz(addQuizRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<AddQuizResponse>{
                override fun onSubscribe(d: Disposable) { }

                override fun onNext(t: AddQuizResponse) {
                    state.postValue(AdminAddQuizFragmentVS.QuizAdded)
                }

                override fun onError(e: Throwable) {
                    state.postValue(AdminAddQuestionFragmentVS.Error)
                }

                override fun onComplete() { }
            })
    }

    fun addTopic(addTopicRequest: AddTopicRequest){
        apiSource.addTopic(addTopicRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<TopicResponse>{
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: TopicResponse) {
                    state.postValue(AddTopicVS.TopicAdded)
                }

                override fun onError(e: Throwable) {
                    state.postValue(AddTopicVS.Error)
                }

                override fun onComplete() { }
            })
    }
}