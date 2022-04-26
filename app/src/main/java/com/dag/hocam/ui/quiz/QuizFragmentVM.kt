package com.dag.hocam.ui.quiz

import com.dag.hocam.application.HocamVM
import com.dag.hocam.data.quiz.*
import com.dag.hocam.network.ApiSource
import com.dag.hocam.ui.quiz.quizfail.QuizFailVS
import com.dag.hocam.ui.quiz.quizresult.QuizFragmentVS
import com.dag.hocam.ui.quiz.quizresult.QuizResultFragmentVS
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class QuizFragmentVM @Inject constructor(
    val apiSource: ApiSource
): HocamVM() {

    fun getQuizQuestions(getQuestionByQuiz: GetQuestionByQuiz){
        apiSource.getQuizzesByName(getQuestionByQuiz)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object: Observer<List<QuestionResponse>>{
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

    fun completeQuestions(completeQuestionRequest: CompleteQuestionRequest){
        apiSource.completeQuestion(completeQuestionRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object: Observer<List<CompleteQuestionResponse>>{
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: List<CompleteQuestionResponse>) {
                    if (t.isNotEmpty()){
                        state.postValue(QuizFailVS.QuizCompleted)
                    }
                }

                override fun onError(e: Throwable) {
                    state.postValue(QuizFailVS.Error)
                }

                override fun onComplete() {}
            })
    }

    fun finishQuiz(completeQuizRequest: CompleteQuizRequest){
        apiSource.completeQuiz(completeQuizRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object: Observer<CompleteQuizRequest>{
                override fun onSubscribe(d: Disposable) {  }

                override fun onNext(t: CompleteQuizRequest) {
                    state.postValue(QuizResultFragmentVS.FinishQuiz)
                }

                override fun onError(e: Throwable) { }

                override fun onComplete() { }

            })
    }

}