package com.dag.hocam.ui.splash

import androidx.lifecycle.viewModelScope
import com.dag.hocam.application.HocamVM
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashVM: HocamVM() {

    fun startTimer(){
        viewModelScope.launch {
            delay(4000L)
            state.postValue(SplashVS.StartApplication)
        }
    }
}