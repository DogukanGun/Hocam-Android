package com.dag.hocam.ui.useroperation

import com.dag.hocam.application.HocamVM
import com.dag.hocam.data.user.LoginRequest
import com.dag.hocam.data.user.RegisterRequest
import com.dag.hocam.network.ApiSource
import javax.inject.Inject

class UserOperationFragmentVM @Inject constructor(
    val apiSource: ApiSource
): HocamVM() {

    fun register(registerRequest: RegisterRequest){

    }

    fun login(loginRequest: LoginRequest){

    }
}