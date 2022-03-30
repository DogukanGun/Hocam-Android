package com.dag.hocam.ui.admin.question

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.quiz.*
import com.dag.hocam.data.topic.TopicResponse
import com.dag.hocam.databinding.FragmentAdminAddQuestionBinding
import com.dag.hocam.databinding.FragmentAdminAddQuizBinding
import com.dag.hocam.ui.admin.AdminFragmentVM
import com.dag.hocam.ui.admin.AdminFragmentVS
import javax.inject.Inject

class AdminAddQuestionFragment: HocamFragment<AdminFragmentVM,FragmentAdminAddQuestionBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_admin_add_question

    override fun getLayoutVM(): AdminFragmentVM = adminFragmentVM

    @Inject
    lateinit var adminFragmentVM: AdminFragmentVM

    lateinit var getContent : ActivityResultLauncher<String>
    lateinit var adapter: AdminAddQuizAdapter

    var questionList = mutableListOf<QuestionResponse>()
    var selectedQuestionId = 0
    var selectedQuiz = Quiz(0,"", emptyList())
    var selectedTopic = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        showProgress()
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
        val questionList = adapter.questionList
        val requestList = mutableListOf<AddQuestionRequest>()
        for (question in questionList){
            requestList.add(AddQuestionRequest(question.question,question.correctAnswer,0))
        }
        showProgress()
        viewModel?.addQuestion(requestList.toList())
    }

    private val adapterListener = object : AdminAddQuizAdapter.AdminAddQuizAdapterListener {
        override fun imageClicked(quizId:Int) {
            selectedQuestionId = quizId
            getContent.launch("image/*")
        }
    }

    private fun createListForRV(){
        for (index in 0 until 15){
            questionList.add(QuestionResponse("","","",""))
        }
    }

    private fun registerActivityResultLauncher(){
        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            questionList[selectedQuestionId].questionUrl = uri.toString()
            adapter.notifyDataSetChanged()
        }
    }

    private fun setQuiz(){
        val getQuizRequestBody = GetQuizRequest(selectedQuestionId,false)
        viewModel?.getAllQuizzes(getQuizRequestBody)
    }

    private fun setTopic(topicList:List<TopicResponse>){
        val newList = topicList.map { it.topicName }
        binding?.topicSpinnerS?.adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,newList)
        binding?.topicSpinnerS?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedTopic = topicList[p2].id
                setQuiz()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedTopic = topicList[0].id
                setQuiz()
            }
        }
    }

    private fun setQuizSpinnerAdapter(quizList:List<Quiz>){
        val newList = quizList.map { it.quizName }
        binding?.quizSpinnerS?.adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,newList)
        binding?.quizSpinnerS?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedQuiz = quizList[p2]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedQuiz = quizList[0]
            }
        }
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            is AdminFragmentVS.SetTopic ->{
                showProgress()
                setTopic(state.topicList)
            }
            AdminAddQuestionFragmentVS.QuestionAdded ->{
                showProgress()
                finishActivity()
            }
            AdminAddQuestionFragmentVS.Error ->{
                showErrorProgress()
            }
            is AdminAddQuestionFragmentVS.SetQuiz ->{
                showProgress()
                setQuizSpinnerAdapter(state.quizList)
            }
        }
    }
}