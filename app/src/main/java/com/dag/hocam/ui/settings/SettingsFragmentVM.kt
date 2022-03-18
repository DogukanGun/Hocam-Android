package com.dag.hocam.ui.settings

import com.dag.hocam.application.HocamSessionManager
import com.dag.hocam.application.HocamVM
import com.dag.hocam.data.questionfromuser.AddQuestionFromUserRequest
import com.dag.hocam.data.questionfromuser.AddQuestionFromUserResponse
import com.dag.hocam.data.quiz.SolvedQuizResponse
import com.dag.hocam.data.session.SessionKey
import com.dag.hocam.network.ApiSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SettingsFragmentVM @Inject constructor(
    val sessionManager: HocamSessionManager,
    val apiSource: ApiSource
    ): HocamVM() {

    fun getSolvedQuestion(){
        apiSource.getAllQuestionFromUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object: Observer<List<AddQuestionFromUserResponse>>{
                override fun onSubscribe(d: Disposable) { }
                override fun onNext(t: List<AddQuestionFromUserResponse>) {
                    state.postValue(SettingsFragmentVS.SetQuizzes(t))
                }
                override fun onError(e: Throwable) {
                    state.postValue(SettingsFragmentVS.Error)
                }
                override fun onComplete() { }
            })
    }

    fun getNotAnsweredQuestion(id:Int){
        apiSource.getQuestionFromUser(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object :Observer<AddQuestionFromUserResponse>{
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: AddQuestionFromUserResponse) {
                    state.postValue(SettingsFragmentVS.SetNotAnsweredQuestion(t))
                }

                override fun onError(e: Throwable) {
                    state.postValue(SettingsFragmentVS.Error)
                }

                override fun onComplete() {}

            })
    }

    fun getSolvedQuizzes(){
        sessionManager.getData<String>(SessionKey.USERNAME.name)?.let {
            apiSource.getAllSolvedQuizzes(it)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :Observer<List<SolvedQuizResponse>>{
                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(t: List<SolvedQuizResponse>) {
                        state.postValue(SettingsFragmentVS.SetSolvedQuizzes(t))
                    }

                    override fun onError(e: Throwable) {
                        state.postValue(SettingsFragmentVS.Error)
                    }

                    override fun onComplete() { }

                })
        }

    }
}