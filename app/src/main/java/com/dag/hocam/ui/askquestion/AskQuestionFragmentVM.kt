package com.dag.hocam.ui.askquestion

import com.dag.hocam.application.HocamVM
import com.dag.hocam.data.questionfromuser.AddQuestionFromUserRequest
import com.dag.hocam.network.ApiSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AskQuestionFragmentVM @Inject constructor(val apiSource: ApiSource): HocamVM() {

    fun uploadQuestion(addQuestionFromUserRequest:AddQuestionFromUserRequest){
        apiSource.addQuestionFromUser(addQuestionFromUserRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object :Observer<AddQuestionFromUserRequest>{
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: AddQuestionFromUserRequest) {
                    state.postValue(AskFragmentVS.QuestionSent)
                }

                override fun onError(e: Throwable) {
                    state.postValue(AskFragmentVS.Error)
                }

                override fun onComplete() {}
            })
    }
}