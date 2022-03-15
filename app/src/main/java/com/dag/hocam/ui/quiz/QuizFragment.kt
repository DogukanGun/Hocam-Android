package com.dag.hocam.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.application.HocamVS
import com.dag.hocam.application.IntentConstant
import com.dag.hocam.data.quiz.QuestionResponse
import com.dag.hocam.data.quiz.Quiz
import com.dag.hocam.databinding.FragmentQuizBinding
import javax.inject.Inject

class QuizFragment: HocamFragment<QuizFragmentVM, FragmentQuizBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_quiz

    override fun getLayoutVM(): QuizFragmentVM = quizFragmentVM

    @Inject
    lateinit var quizFragmentVM: QuizFragmentVM

    private lateinit var questionList: List<QuestionResponse>

    private var totalPoint = 0
    private var questionNumber = 0


    companion object{
        fun getInstance(quizName:String):QuizFragment{
            return QuizFragment().apply {
                arguments = Bundle().apply {
                    putString(IntentConstant.QUIZ_NAME.name,quizName)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        arguments?.getString(IntentConstant.QUIZ_NAME.name,"")?.let {
            viewModel?.getQuizQuestions(it)
        }
        return view
    }

    private fun startQuiz(){

    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            is QuizFragmentVS.SetQuestions ->{
                questionList = state.questionList
                startQuiz()
            }
        }
    }
}