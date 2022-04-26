package com.dag.hocam.ui.topic

import com.dag.hocam.application.HocamVM
import com.dag.hocam.data.quiz.GetQuizRequest
import com.dag.hocam.data.quiz.Quiz
import com.dag.hocam.data.topic.GetAllTopicResponse
import com.dag.hocam.data.topic.TopicResponse
import com.dag.hocam.network.ApiSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopicFragmentVM @Inject constructor(
    val apiSource: ApiSource
): HocamVM() {

    fun getTopics(){
        apiSource.getAllTopics()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object :Observer<List<GetAllTopicResponse>>{
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<GetAllTopicResponse>) {
                    val topicResponseList = mutableListOf<TopicResponse>()
                    for (index in t){
                        val topicResponse = TopicResponse(index.topicName,index.id.toInt())
                        topicResponseList.add(topicResponse)
                    }
                    state.postValue(TopicFragmentVS.SetTopic(topicResponseList))
                }

                override fun onError(e: Throwable) {
                    state.postValue(TopicFragmentVS.Error)
                }

                override fun onComplete() {
                }

            })
    }

    fun getSubjects(){
        state.postValue(TopicFragmentVS.Soon)
    }

    fun getQuizzes(getQuizRequest: GetQuizRequest){
        apiSource.getAllQuizzesByTopic(getQuizRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object :Observer<List<Quiz>>{
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<Quiz>) {
                    state.postValue(TopicFragmentVS.SetQuizzes(t))
                }

                override fun onError(e: Throwable) {
                    state.postValue(TopicFragmentVS.Error)
                }

                override fun onComplete() {
                }

            })
    }
}