package com.dag.hocam.ui.admin

import com.dag.hocam.application.HocamVM
import com.dag.hocam.data.quiz.AddQuizRequest
import com.dag.hocam.data.quiz.AddQuizResponse
import com.dag.hocam.data.topic.GetAllTopicResponse
import com.dag.hocam.data.topic.TopicResponse
import com.dag.hocam.network.ApiSource
import com.dag.hocam.ui.topic.TopicFragmentVS
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
                    state.postValue(AdminAddQuizFragmentVS.SetTopic(topicResponseList))
                }

                override fun onError(e: Throwable) {
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
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: AddQuizResponse) {
                    state.postValue(AdminAddQuizFragmentVS.QuizAdded)
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

            })
    }
}