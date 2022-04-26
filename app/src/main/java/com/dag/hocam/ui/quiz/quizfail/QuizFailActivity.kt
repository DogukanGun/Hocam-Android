package com.dag.hocam.ui.quiz.quizfail

import android.os.Bundle
import com.dag.hocam.R
import com.dag.hocam.application.HocamActivity
import com.dag.hocam.application.IntentConstant
import com.dag.hocam.data.quiz.CompleteQuestionRequest
import com.dag.hocam.databinding.ActivityQuizFailBinding
import com.dag.hocam.ui.quiz.QuizFragmentVM
import javax.inject.Inject

class QuizFailActivity: HocamActivity<QuizFragmentVM,ActivityQuizFailBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_quiz_fail

    override fun getLayoutVM(): QuizFragmentVM = quizFragmentVM

    @Inject
    lateinit var quizFragmentVM: QuizFragmentVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getParcelableExtra<CompleteQuestionRequest>(IntentConstant.COMPETED_QUESTION.name)?.let {
            addFragment(QuizFailFragment.getInstance(it))
        }
    }
}