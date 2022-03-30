package com.dag.hocam.ui.admin.topic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.quiz.AddQuizRequest
import com.dag.hocam.data.topic.AddTopicRequest
import com.dag.hocam.databinding.FragmentAdminAddTopicBinding
import com.dag.hocam.ui.admin.AdminFragmentVM
import javax.inject.Inject

class AddTopicFragment: HocamFragment<AdminFragmentVM,FragmentAdminAddTopicBinding>() {


    override fun getLayoutId(): Int = R.layout.fragment_admin_add_topic

    override fun getLayoutVM(): AdminFragmentVM = adminFragmentVM

    @Inject
    lateinit var adminFragmentVM: AdminFragmentVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding?.submitButtonBTN?.setOnClickListener(buttonListener)
        return view
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            AddTopicVS.TopicAdded ->{
                showProgress()
                finishActivity()
            }
            AddTopicVS.Error ->{
                showErrorProgress()
            }
        }
    }

    private val buttonListener = View.OnClickListener {
        binding?.topicNameET?.text?.toString()?.let {
            showProgress()
            viewModel?.addTopic(AddTopicRequest(it))
        }
    }
}