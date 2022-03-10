package com.dag.hocam.ui.home

import android.os.Bundle
import android.os.PersistableBundle
import com.dag.hocam.R
import com.dag.hocam.application.HocamActivity
import com.dag.hocam.application.HocamVM
import com.dag.hocam.databinding.ActivityHomeBinding
import javax.inject.Inject

class HomeActivity: HocamActivity<HomeActivityVM,ActivityHomeBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_home

    override fun getLayoutVM(): HomeActivityVM = homeVM

    @Inject
    lateinit var homeVM: HomeActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(HomeFragment())
    }
}