package com.dag.hocam.ui.quiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide
import com.dag.hocam.R
import com.dag.hocam.application.*
import com.dag.hocam.data.quiz.*
import com.dag.hocam.data.session.SessionKey
import com.dag.hocam.databinding.FragmentQuizBinding
import com.dag.hocam.ui.quiz.quizfail.QuizFailActivity
import com.dag.hocam.ui.quiz.quizfail.QuizFailFragment
import com.dag.hocam.ui.quiz.quizresult.QuizFragmentVS
import com.dag.hocam.ui.quiz.quizresult.QuizResultFragment
import javax.inject.Inject

class QuizFragment: HocamFragment<QuizFragmentVM, FragmentQuizBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_quiz

    override fun getLayoutVM(): QuizFragmentVM = quizFragmentVM

    override fun hasNextButton(): Boolean = true

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    @Inject
    lateinit var quizFragmentVM: QuizFragmentVM

    @Inject
    lateinit var sessionManager: HocamSessionManager

    private lateinit var questionList: List<QuestionResponse>

    private var totalPoint = 0
    private var selectedAnswer:Options? = null
    private var isAnswerSelected = false
    private var questionNumber = 0
    private var quiz:Quiz? = null

    companion object{
        fun getInstance(quiz:Quiz):QuizFragment{
            return QuizFragment().apply {
                arguments = Bundle().apply {
                    putString(IntentConstant.QUIZ_NAME.name,quiz.quizName)
                    putInt(IntentConstant.QUIZ_ID.name,quiz.id)
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
        setNextButtonListener(nextButtonListener)
        setActionBar()
        getQuiz()
        binding?.let {
            it.answerABTN.setOnClickListener(answerButtonsListener)
            it.answerBBTN.setOnClickListener(answerButtonsListener)
            it.answerCBTN.setOnClickListener(answerButtonsListener)
            it.answerDBTN.setOnClickListener(answerButtonsListener)
            it.answerEBTN.setOnClickListener(answerButtonsListener)
        }
        createActivityResultLauncher()
        return view
    }

    private fun getQuiz(){
        safeLet(arguments?.getString(IntentConstant.QUIZ_NAME.name),
            arguments?.getInt(IntentConstant.QUIZ_ID.name)){ quizName,quizId ->
            showProgress()
            val getQuestionByQuiz = GetQuestionByQuiz(quizName,15,true)
            viewModel?.getQuizQuestions(getQuestionByQuiz)
            quiz = Quiz(quizId,quizName, emptyList())
        }
    }

    private fun chooseButton(button: AppCompatButton){
        button.setBackgroundResource(R.drawable.bg_button)
        button.setTextAppearance(R.style.StandartButton)
    }

    private fun notChooseButton(button: AppCompatButton){
        button.setBackgroundResource(R.drawable.bg_secondary_button)
        button.setTextAppearance(R.style.SecondaryButton)
    }

    private val answerButtonsListener = View.OnClickListener {
        binding?.let { bindingForButtons->
            when(it.id){
                R.id.answerABTN ->{
                    chooseButton(bindingForButtons.answerABTN)
                    notChooseButton(bindingForButtons.answerBBTN)
                    notChooseButton(bindingForButtons.answerCBTN)
                    notChooseButton(bindingForButtons.answerDBTN)
                    notChooseButton(bindingForButtons.answerEBTN)
                    selectedAnswer = Options.A
                }
                R.id.answerBBTN ->{
                    notChooseButton(bindingForButtons.answerABTN)
                    chooseButton(bindingForButtons.answerBBTN)
                    notChooseButton(bindingForButtons.answerCBTN)
                    notChooseButton(bindingForButtons.answerDBTN)
                    notChooseButton(bindingForButtons.answerEBTN)
                    selectedAnswer = Options.B
                }
                R.id.answerCBTN ->{
                    notChooseButton(bindingForButtons.answerABTN)
                    notChooseButton(bindingForButtons.answerBBTN)
                    chooseButton(bindingForButtons.answerCBTN)
                    notChooseButton(bindingForButtons.answerDBTN)
                    notChooseButton(bindingForButtons.answerEBTN)
                    selectedAnswer = Options.C
                }
                R.id.answerDBTN ->{
                    notChooseButton(bindingForButtons.answerABTN)
                    notChooseButton(bindingForButtons.answerBBTN)
                    notChooseButton(bindingForButtons.answerCBTN)
                    chooseButton(bindingForButtons.answerDBTN)
                    notChooseButton(bindingForButtons.answerEBTN)
                    selectedAnswer = Options.D
                }
                R.id.answerEBTN ->{
                    notChooseButton(bindingForButtons.answerABTN)
                    notChooseButton(bindingForButtons.answerBBTN)
                    notChooseButton(bindingForButtons.answerCBTN)
                    notChooseButton(bindingForButtons.answerDBTN)
                    chooseButton(bindingForButtons.answerEBTN)
                    selectedAnswer = Options.E
                }
            }
            isAnswerSelected = true
        }
    }

    private fun createActivityResultLauncher(){
        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            if (it.resultCode == Activity.RESULT_OK){
                getQuiz()
            }else{
                finishActivity()
            }
        }
    }

    private val nextButtonListener = View.OnClickListener {
        if (isAnswerSelected){
            selectedAnswer?.let {
                if (it.name.lowercase() == questionList[questionNumber].correctAnswer.lowercase()){
                    totalPoint += Constant.quizQuestionPoint
                    nextQuestion()
                }else{
                    safeLet(sessionManager.getData<String>(SessionKey.USERNAME.name),quiz?.id){ username,quizId ->
                        val completeQuestionRequest = CompleteQuestionRequest(username,quizId,questionList.slice(IntRange(0,questionNumber)).map { it.id.toInt() })
                        val intent = Intent(requireContext(),QuizFailActivity::class.java).also {
                            it.putExtra(IntentConstant.COMPETED_QUESTION.name,completeQuestionRequest)
                        }
                        activityResultLauncher.launch(intent)
                    }
                }

            }
        }else{
            Toast.makeText(requireContext(),R.string.quiz_next_button_error_message,Toast.LENGTH_LONG).show()
        }
    }

    private fun nextQuestion(){
        binding?.let {
            notChooseButton(it.answerABTN)
            notChooseButton(it.answerBBTN)
            notChooseButton(it.answerCBTN)
            notChooseButton(it.answerDBTN)
            notChooseButton(it.answerEBTN)
            selectedAnswer = null
            questionNumber+=1
            it.totalPointTV.text = "Toplam puan: ${totalPoint} / Soru: ${questionNumber} "
            isAnswerSelected = false
            if (questionList.size > questionNumber)
                startQuiz()
            else
                finishQuiz()
        }
    }

    private fun finishQuiz(){
        quiz?.id?.let {
            addFragment(QuizResultFragment.getInstance(totalPoint,it))
        }
    }

    private fun startQuiz(){
        val decodedBytes: ByteArray = Base64.decode(questionList[questionNumber].question, Base64.DEFAULT)
        binding?.questionIV?.let { Glide.with(this).load(decodedBytes).fitCenter().into(it) }
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            is QuizFragmentVS.SetQuestions ->{
                showProgress()
                questionList = state.questionList
                startQuiz()
            }
            QuizFragmentVS.Error ->{
                showErrorProgress()
            }
        }
    }
}