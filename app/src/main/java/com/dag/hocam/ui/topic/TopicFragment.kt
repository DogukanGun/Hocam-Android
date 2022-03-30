package com.dag.hocam.ui.topic

import android.content.Intent
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
import com.dag.hocam.data.quiz.GetQuizRequest
import com.dag.hocam.data.quiz.Quiz
import com.dag.hocam.data.topic.TopicPath
import com.dag.hocam.data.topic.TopicResponse
import com.dag.hocam.databinding.FragmentTopicBinding
import com.dag.hocam.ui.quiz.QuizActivity
import com.dag.hocam.ui.quiz.QuizAdapter
import com.dag.hocam.ui.quiz.QuizFragment
import com.dag.hocam.ui.quiz.QuizListener
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
        showProgress()
        viewModel?.getTopics()
        arguments?.getString(IntentConstant.TOPIC_PATH.name,TopicPath.QUIZ.name)?.let {
            topicPath = TopicPath.valueOf(it)
        }
        return view
    }

    fun refresh(){
        showProgress()
        viewModel?.getTopics()
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            is TopicFragmentVS.SetTopic ->{
                showProgress()
                setTopicAdapter(state.topicList)
            }
            is TopicFragmentVS.SetQuizzes ->{
                showProgress()
                setQuizAdapter(state.quizList)
            }
            TopicFragmentVS.Error ->{
                showErrorProgress()
            }
            TopicFragmentVS.Soon ->{
                setSoonAdapter()
            }
        }
    }

    private fun setSoonAdapter(){
        binding?.topicListRV?.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = TopicAdapter(emptyList()).also {
                it.soonActive = true
            }
        }
    }

    private fun setQuizAdapter(quizList:List<Quiz>){
        binding?.topicListRV?.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = QuizAdapter(quizList).also {
                it.listener = quizListener
            }
        }
    }

    private val quizListener = object : QuizListener {
        override fun quizClicked(quiz: Quiz) {
            val intent = Intent(requireContext(),QuizActivity::class.java)
            intent.putExtra(IntentConstant.QUIZ_NAME.name,quiz.quizName)
            intent.putExtra(IntentConstant.QUIZ_ID.name,quiz.id)
            startActivity(intent)
        }
    }

    private fun setTopicAdapter(topicList:List<TopicResponse>){
        val adapter = TopicAdapter(topicList).also {
            it.listener = topicRecyclerViewListener
        }
        binding?.topicListRV?.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private val topicRecyclerViewListener = object :TopicListener{
        override fun topicClicked(topic: TopicResponse) {
            if (topicPath == TopicPath.QUIZ){
                showProgress()
                viewModel?.getQuizzes(GetQuizRequest(topic.id,true))
            }else if (topicPath == TopicPath.SUBJECT){
                viewModel?.getSubjects()
            }
        }
    }



}