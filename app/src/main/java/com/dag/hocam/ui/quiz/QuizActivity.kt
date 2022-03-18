package com.dag.hocam.ui.quiz

import android.os.Bundle
import android.widget.Toast
import com.dag.hocam.R
import com.dag.hocam.application.HocamActivity
import com.dag.hocam.application.IntentConstant
import com.dag.hocam.data.quiz.Quiz
import com.dag.hocam.databinding.ActivityQuizBinding
import javax.inject.Inject

class QuizActivity: HocamActivity<QuizActivityVM,ActivityQuizBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_quiz

    override fun getLayoutVM(): QuizActivityVM = quizActivityVM

    @Inject
    lateinit var quizActivityVM: QuizActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        safeLet(intent.getStringExtra(IntentConstant.QUIZ_NAME.name),
            intent.getIntExtra(IntentConstant.QUIZ_ID.name,0)){ quizName,quizId ->
            val quiz = Quiz(quizId,quizName, emptyList())
            addFragment(QuizFragment.getInstance(quiz))
        }

    }

    override fun onBackPressed() {
        Toast.makeText(applicationContext,R.string.quiz_back_button_error_message,Toast.LENGTH_LONG).show()
    }
}