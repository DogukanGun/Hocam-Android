package com.dag.hocam.ui.admin.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.quiz.AddQuizRequest
import com.dag.hocam.data.topic.TopicResponse
import com.dag.hocam.databinding.FragmentAdminAddQuizBinding
import com.dag.hocam.ui.admin.AdminFragmentVM
import com.dag.hocam.ui.admin.AdminFragmentVS
import javax.inject.Inject

class AdminAddQuizFragment: HocamFragment<AdminFragmentVM,FragmentAdminAddQuizBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_admin_add_quiz

    override fun getLayoutVM(): AdminFragmentVM = adminFragmentVM

    @Inject
    lateinit var adminFragmentVM: AdminFragmentVM

    private var selectedTopic = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding?.submitButtonBTN?.setOnClickListener(submitButtonListener)
        showProgress()
        viewModel?.getTopics()
        return view
    }

    val submitButtonListener = View.OnClickListener {
        showProgress()
        val addQuizRequest =
            binding?.quizNameET?.text?.toString()?.let { it1 -> AddQuizRequest(it1,selectedTopic) }
        addQuizRequest?.let { it1 -> viewModel?.addQuiz(it1) }
    }

    private fun setTopic(topicList:List<TopicResponse>){
        val newList = topicList.map { it.topicName }
        binding?.topicSpinnerS?.adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,newList)
        binding?.topicSpinnerS?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedTopic = topicList[p2].id
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedTopic = topicList[0].id
            }
        }
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            AdminAddQuizFragmentVS.QuizAdded ->{
                showProgress()
                finishActivity()
            }
            AdminAddQuizFragmentVS.Error ->{
                showErrorProgress()
            }
            is AdminFragmentVS.SetTopic ->{
                showProgress()
                setTopic(state.topicList)
            }
        }
    }

}