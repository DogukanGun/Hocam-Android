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
import com.dag.hocam.data.quiz.Quiz
import com.dag.hocam.databinding.FragmentQuizBinding
import javax.inject.Inject

class QuizFragment: HocamFragment<QuizFragmentVM, FragmentQuizBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_quiz

    override fun getLayoutVM(): QuizFragmentVM = quizFragmentVM

    @Inject
    lateinit var quizFragmentVM: QuizFragmentVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        viewModel?.getQuizzes()
        return view
    }

    private fun setAdapter(quizList: List<Quiz>){
        binding?.quizListRV?.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = QuizAdapter(quizList).also {
                it.listener = quizListener
            }
        }
    }

    private val quizListener = object :QuizListener{
        override fun quizClicked(quiz: Quiz) {
            Toast.makeText(requireContext(),"${quiz.quizName} is clicked",Toast.LENGTH_LONG).show()
        }
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            is QuizFragmentVS.SetAdapter ->{
                setAdapter(state.quizList)
            }
        }
    }
}