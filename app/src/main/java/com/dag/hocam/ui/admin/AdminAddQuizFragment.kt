package com.dag.hocam.ui.admin

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.quiz.QuestionResponse
import com.dag.hocam.data.quiz.Quiz
import com.dag.hocam.data.topic.TopicResponse
import com.dag.hocam.databinding.FragmentAdminAddQuizBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class AdminAddQuizFragment: HocamFragment<AdminFragmentVM,FragmentAdminAddQuizBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_admin_add_quiz

    override fun getLayoutVM(): AdminFragmentVM = adminFragmentVM

    @Inject
    lateinit var adminFragmentVM: AdminFragmentVM

    lateinit var getContent : ActivityResultLauncher<String>
    lateinit var adapter: AdminAddQuizAdapter

    var questionList = mutableListOf<QuestionResponse>()
    var selectedQuestionId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        viewModel?.getTopics()
        createListForRV()
        registerActivityResultLauncher()
        binding?.apply {
            adapter = AdminAddQuizAdapter(questionList,requireContext(),10).apply {
                this.listener = adapterListener
            }
            questionListRV.adapter = adapter
            questionListRV.layoutManager = LinearLayoutManager(requireContext())
            questionListRV.addItemDecoration(DividerItemDecoration(requireContext(),
                LinearLayoutManager.VERTICAL))
            addQuizBTN.setOnClickListener(submitButtonListener)
        }
        return view
    }

    private val submitButtonListener = View.OnClickListener {
        var list = adapter.questionList
    }

    private val adapterListener = object :AdminAddQuizAdapter.AdminAddQuizAdapterListener{
        override fun imageClicked(quizId:Int) {
            selectedQuestionId = quizId
            getContent.launch("image/*")
        }
    }

    private fun createListForRV(){
        for (index in 0 until 12){
            questionList.add(QuestionResponse("","",""))
        }
    }

    private fun registerActivityResultLauncher(){
        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            questionList[selectedQuestionId].questionUrl = uri.toString()
            adapter.notifyDataSetChanged()
        }
    }

    private fun setTopic(topicList:List<TopicResponse>){
        var newList = topicList.map { it.topicName }
        binding?.spinner?.adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,newList)
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            is AdminAddQuizFragmentVS.SetTopic ->{
                setTopic(state.topicList)
            }
        }
    }
}