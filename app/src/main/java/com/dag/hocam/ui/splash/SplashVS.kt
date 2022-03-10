package com.dag.hocam.ui.splash

import com.dag.hocam.application.HocamVS

sealed class SplashVS: HocamVS {
    object StartApplication: SplashVS()
}