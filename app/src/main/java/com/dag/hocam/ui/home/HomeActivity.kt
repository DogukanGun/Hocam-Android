package com.dag.hocam.ui.home

import android.os.Bundle
import android.os.PersistableBundle
import com.dag.hocam.R
import com.dag.hocam.application.HocamActivity
import com.dag.hocam.application.HocamVM
import com.dag.hocam.databinding.ActivityHomeBinding
import com.dag.hocam.ui.askquestion.AskQuestionFragment
import com.google.android.material.navigation.NavigationBarView
import javax.inject.Inject

class HomeActivity: HocamActivity<HomeActivityVM,ActivityHomeBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_home

    override fun getLayoutVM(): HomeActivityVM = homeVM

    @Inject
    lateinit var homeVM: HomeActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding?.bottomNav?.setOnItemSelectedListener(bottomNavigationListener)

    }

    private var bottomNavigationListener = NavigationBarView.OnItemSelectedListener {
        when(it.itemId){
            R.id.quiz_bottom_navigation ->{
                addFragment(HomeFragment())
            }
            R.id.ask_question_bottom_navigation ->{
                addFragment(AskQuestionFragment())
            }
            R.id.subject_bottom_navigation ->{
                addFragment(HomeFragment())
            }
            else -> {
                addFragment(HomeFragment())
            }
        }
        true
    }
}