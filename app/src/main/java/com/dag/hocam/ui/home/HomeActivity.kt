package com.dag.hocam.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.widget.Toast
import com.dag.hocam.R
import com.dag.hocam.application.HocamActivity
import com.dag.hocam.application.HocamVM
import com.dag.hocam.data.topic.TopicPath
import com.dag.hocam.databinding.ActivityHomeBinding
import com.dag.hocam.ui.askquestion.AskQuestionFragment
import com.dag.hocam.ui.topic.TopicFragment
import com.google.android.material.navigation.NavigationBarView
import javax.inject.Inject

class HomeActivity: HocamActivity<HomeActivityVM,ActivityHomeBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_home

    override fun getLayoutVM(): HomeActivityVM = homeVM

    override fun hasSettingButton(): Boolean = true

    @Inject
    lateinit var homeVM: HomeActivityVM
    lateinit var currentTopicFragment:TopicFragment
    private var doubleBackToExitPressedOnce = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBar()
        binding?.bottomNav?.setOnItemSelectedListener(bottomNavigationListener)
        currentTopicFragment = TopicFragment.getInstance(TopicPath.QUIZ)
        addFragment(currentTopicFragment)
    }

    private var bottomNavigationListener = NavigationBarView.OnItemSelectedListener {
        when(it.itemId){
            R.id.quiz_bottom_navigation ->{
                currentTopicFragment = TopicFragment.getInstance(TopicPath.QUIZ)
                addFragment(currentTopicFragment)
            }
            R.id.ask_question_bottom_navigation ->{
                addFragment(AskQuestionFragment())
            }
            R.id.subject_bottom_navigation ->{
                currentTopicFragment = TopicFragment.getInstance(TopicPath.SUBJECT)
                addFragment(currentTopicFragment)
            }
            else -> {
                currentTopicFragment = TopicFragment.getInstance(TopicPath.QUIZ)
                addFragment(currentTopicFragment)
            }
        }
        true
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAndRemoveTask()
        }
        currentTopicFragment.refresh()
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}