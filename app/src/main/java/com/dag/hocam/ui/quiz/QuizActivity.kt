package com.dag.hocam.ui.quiz

import android.os.Bundle
import com.dag.hocam.R
import com.dag.hocam.application.HocamActivity
import com.dag.hocam.databinding.ActivityQuizBinding
import javax.inject.Inject

class QuizActivity: HocamActivity<QuizActivityVM,ActivityQuizBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_quiz

    override fun getLayoutVM(): QuizActivityVM = quizActivityVM

    @Inject
    lateinit var quizActivityVM: QuizActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(QuizFragment())
    }

}