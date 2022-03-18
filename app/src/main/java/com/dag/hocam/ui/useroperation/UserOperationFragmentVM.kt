package com.dag.hocam.ui.useroperation

import io.reactivex.Observer
import androidx.lifecycle.viewModelScope
import com.dag.hocam.application.Constant
import com.dag.hocam.application.HocamSessionManager
import com.dag.hocam.application.HocamVM
import com.dag.hocam.data.session.SessionKey
import com.dag.hocam.data.user.AuthenticationResponse
import com.dag.hocam.data.user.LoginRequest
import com.dag.hocam.data.user.RegisterRequest
import com.dag.hocam.network.ApiSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

class UserOperationFragmentVM @Inject constructor(
    val apiSource: ApiSource,
    val sessionManager: HocamSessionManager
): HocamVM() {

    fun register(registerRequest: RegisterRequest){
            apiSource.register(registerRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :Observer<AuthenticationResponse>{
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: AuthenticationResponse) {
                        if (t.success){
                            state.postValue(UserOperationFragmentVS.Login)
                        }
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }

                })
        }

    fun login(loginRequest: LoginRequest){
            apiSource.login(loginRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :Observer<AuthenticationResponse>{
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: AuthenticationResponse) {
                        if (t.success){
                            var token = ""
                            t.data?.token?.let { token = it }
                            var userType = ""
                            t.data?.userType?.let { userType = it }
                            Constant.token = token
                            var username = ""
                            t.data?.username?.let { username = it }
                            sessionManager.putData(SessionKey.TOKEN.name,token)
                            sessionManager.putData(SessionKey.USERTYPE.name,userType)
                            sessionManager.putData(SessionKey.USERNAME.name,username)
                            state.postValue(UserOperationFragmentVS.StartApplication)
                        }
                    }
                    override fun onError(e: Throwable) {
                        state.postValue(UserOperationFragmentVS.Error)
                    }

                    override fun onComplete() {
                    }

                })

        }
}
