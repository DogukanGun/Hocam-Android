package com.dag.hocam.ui.topic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.application.HocamVS
import com.dag.hocam.application.IntentConstant
import com.dag.hocam.data.topic.TopicPath
import com.dag.hocam.data.topic.TopicResponse
import com.dag.hocam.databinding.FragmentTopicBinding
import com.dag.hocam.ui.quiz.QuizFragment
import javax.inject.Inject
import javax.security.auth.Subject

class TopicFragment: HocamFragment<TopicFragmentVM,FragmentTopicBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_topic

    override fun getLayoutVM(): TopicFragmentVM = topicFragmentVM

    @Inject
    lateinit var topicFragmentVM: TopicFragmentVM

    var topicPath = TopicPath.QUIZ

    companion object{
        fun getInstance(topicPath: TopicPath):TopicFragment{
            return TopicFragment().apply {
                arguments = Bundle().apply {
                    putString(IntentConstant.TOPIC_PATH.name,topicPath.name)
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
        viewModel?.getTopics()
        arguments?.getString(IntentConstant.TOPIC_PATH.name,TopicPath.QUIZ.name)?.let {
            topicPath = TopicPath.valueOf(it)
        }
        return view
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            is TopicFragmentVS.SetTopic ->{
                val adapter = TopicAdapter(state.topicList).also {
                    it.listener = topicRecyclerViewListener
                }
                binding?.topicListRV?.apply {
                    this.adapter = adapter
                    this.layoutManager = LinearLayoutManager(requireContext())
                }

            }
        }
    }

    val topicRecyclerViewListener = object :TopicListener{
        override fun topicClicked(topic: TopicResponse) {
            if (topicPath == TopicPath.QUIZ){
                addFragment(QuizFragment())
            }else if (topicPath == TopicPath.SUBJECT){
                //add subject
            }
        }
    }
}