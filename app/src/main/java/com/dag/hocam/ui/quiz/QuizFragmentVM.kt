package com.dag.hocam.ui.quiz

import com.dag.hocam.application.HocamVM
import com.dag.hocam.data.quiz.Quiz
import com.dag.hocam.network.ApiSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class QuizFragmentVM @Inject constructor(
    val apiSource: ApiSource
): HocamVM() {

    fun getQuizzes(){
        apiSource.getAllQuizzes()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object :Observer<List<Quiz>>{
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<Quiz>) {
                    state.postValue(QuizFragmentVS.SetAdapter(t))
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

            })
    }
}