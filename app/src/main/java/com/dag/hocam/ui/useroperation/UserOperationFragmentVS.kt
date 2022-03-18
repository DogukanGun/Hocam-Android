package com.dag.hocam.ui.useroperation

import com.dag.hocam.application.HocamVS

sealed class UserOperationFragmentVS: HocamVS {

    object Login: UserOperationFragmentVS()
    object StartApplication: UserOperationFragmentVS()
    object Error: UserOperationFragmentVS()
}