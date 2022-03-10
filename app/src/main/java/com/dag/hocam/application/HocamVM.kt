package com.dag.hocam.application

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class HocamVM: ViewModel() {
    val state = MutableLiveData<HocamVS>()
}