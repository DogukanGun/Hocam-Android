package com.dag.hocam.ui.splash

import android.os.Bundle
import android.os.PersistableBundle
import com.dag.hocam.R
import com.dag.hocam.application.HocamActivity
import com.dag.hocam.application.HocamVS
import com.dag.hocam.databinding.ActivitySplashBinding
import com.dag.hocam.ui.home.HomeActivity
import javax.inject.Inject

class SplashActivity: HocamActivity<SplashVM,ActivitySplashBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun getLayoutVM(): SplashVM = splashVM

    @Inject
    lateinit var splashVM: SplashVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel?.startTimer()
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            SplashVS.StartApplication ->{
                startActivity(HomeActivity::class.java)
            }
        }
    }
}