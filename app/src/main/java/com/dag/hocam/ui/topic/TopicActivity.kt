package com.dag.hocam.ui.topic

import android.os.Bundle
import com.dag.hocam.R
import com.dag.hocam.application.HocamActivity
import com.dag.hocam.databinding.ActivityTopicBinding
import javax.inject.Inject

class TopicActivity: HocamActivity<TopicActivityVM,ActivityTopicBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_topic

    override fun getLayoutVM(): TopicActivityVM = topicActivityVM

    @Inject
    lateinit var topicActivityVM: TopicActivityVM

    val topicFragment = TopicFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(topicFragment)
    }

    override fun onBackPressed() {
        topicFragment.refresh()
    }
}
