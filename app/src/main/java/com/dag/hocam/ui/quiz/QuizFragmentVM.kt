package com.dag.hocam.ui.quiz

import com.dag.hocam.application.HocamVM
import com.dag.hocam.data.quiz.QuestionResponse
import com.dag.hocam.data.quiz.Quiz
import com.dag.hocam.network.ApiSource
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class QuizFragmentVM @Inject constructor(
    val apiSource: ApiSource
): HocamVM() {

    fun getQuizQuestions(quizName:String){
        apiSource.getQuizzesByName(quizName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object :Observer<List<QuestionResponse>>{
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<QuestionResponse>) {
                    state.postValue(QuizFragmentVS.SetQuestions(t))
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

            })
    }

}