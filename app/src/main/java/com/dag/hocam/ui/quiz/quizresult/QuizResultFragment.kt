package com.dag.hocam.ui.quiz.quizresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.application.HocamSessionManager
import com.dag.hocam.application.HocamVS
import com.dag.hocam.application.IntentConstant
import com.dag.hocam.data.quiz.CompleteQuizRequest
import com.dag.hocam.data.session.SessionKey
import com.dag.hocam.databinding.FragmentQuizResultBinding
import com.dag.hocam.ui.home.HomeActivity
import com.dag.hocam.ui.quiz.QuizFragmentVM
import javax.inject.Inject

class QuizResultFragment: HocamFragment<QuizFragmentVM,FragmentQuizResultBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_quiz_result

    override fun getLayoutVM(): QuizFragmentVM = quizFragmentVM

    @Inject
    lateinit var quizFragmentVM: QuizFragmentVM

    @Inject
    lateinit var sessionManager: HocamSessionManager

    private var totalPoint = 0
    private var quizId = 0

    companion object{
        fun getInstance(totalPoint:Int,quizId:Int): QuizResultFragment {
            return QuizResultFragment().apply {
                arguments = Bundle().also {
                    it.putInt(IntentConstant.TOTAL_POINT.name,totalPoint)
                    it.putInt(IntentConstant.QUIZ_ID.name,quizId)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)
        safeLet(arguments?.getInt(IntentConstant.TOTAL_POINT.name),arguments?.getInt(IntentConstant.QUIZ_ID.name))
        { totalPoint, quizId ->
            this.quizId = quizId
            this.totalPoint = totalPoint
        }
        binding?.totalPointValueTV?.text = totalPoint.toString()
        binding?.finishButtonBTN?.setOnClickListener(finishButtonListener)
        return view
    }

    private val finishButtonListener = View.OnClickListener {
        sessionManager.getData<String>(SessionKey.USERNAME.name)?.let {
            val completeQuizRequest = CompleteQuizRequest(it, quizId,totalPoint)
            showProgress()
            viewModel?.finishQuiz(completeQuizRequest)
        }
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            QuizResultFragmentVS.FinishQuiz ->{
                showProgress()
                startActivity(HomeActivity::class.java)
            }
            QuizResultFragmentVS.Error ->{
                showErrorProgress()
            }
        }
    }
}