package com.dag.hocam.ui.topic

import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.databinding.FragmentTopicBinding
import javax.inject.Inject

class TopicFragment: HocamFragment<TopicFragmentVM,FragmentTopicBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_topic

    override fun getLayoutVM(): TopicFragmentVM = topicFragmentVM

    @Inject
    lateinit var topicFragmentVM: TopicFragmentVM
}