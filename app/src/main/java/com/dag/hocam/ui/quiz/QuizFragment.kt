package com.dag.hocam.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
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

        return view
    }
}