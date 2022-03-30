package com.dag.hocam.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.databinding.FragmentAdminBinding
import com.dag.hocam.ui.admin.question.AdminAddQuestionFragment
import com.dag.hocam.ui.admin.quiz.AdminAddQuizFragment
import com.dag.hocam.ui.admin.topic.AddTopicFragment
import javax.inject.Inject

class AdminFragment: HocamFragment<AdminFragmentVM,FragmentAdminBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_admin

    override fun getLayoutVM(): AdminFragmentVM = adminFragmentVM

    @Inject
    lateinit var adminFragmentVM: AdminFragmentVM


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)
        binding?.apply {
            this.addTopic.setOnClickListener {
                addFragment(AddTopicFragment())
            }
            this.addQuiz.setOnClickListener {
                addFragment(AdminAddQuizFragment())
            }
            this.addQuestion.setOnClickListener{
                addFragment(AdminAddQuestionFragment())
            }
            this.addSubject.setOnClickListener {

            }
        }
        return view
    }
}