package com.dag.hocam.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.application.HocamVS
import com.dag.hocam.application.IntentConstant
import com.dag.hocam.data.questionfromuser.AddQuestionFromUserResponse
import com.dag.hocam.databinding.FragmentSettingsQuizNotAnsweredQuestionBinding
import javax.inject.Inject

class SettingsQuizNotAnsweredQuestion: HocamFragment<SettingsFragmentVM,FragmentSettingsQuizNotAnsweredQuestionBinding>(){
    override fun getLayoutId(): Int = R.layout.fragment_settings_quiz_not_answered_question

    override fun getLayoutVM(): SettingsFragmentVM = settingsFragmentVM

    @Inject
    lateinit var settingsFragmentVM: SettingsFragmentVM

    companion object{
        fun getInstance(id:Int):SettingsQuizNotAnsweredQuestion{
            return SettingsQuizNotAnsweredQuestion().apply {
                arguments = Bundle().also {
                    it.putInt(IntentConstant.QUIZ_ID.name,id)
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
        arguments?.getInt(IntentConstant.QUIZ_ID.name)?.let {
            viewModel?.getNotAnsweredQuestion(it)
        }
        return view
    }

    private fun setNotAnsweredQuestion(notAnsweredQuestion: AddQuestionFromUserResponse){
        binding?.apply {
            this.emailTV.text = notAnsweredQuestion.responseMail
            Glide.with(requireContext()).load(notAnsweredQuestion.question).into(this.questionImageIV)
        }
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            is SettingsFragmentVS.SetNotAnsweredQuestion ->{
                showProgress()
                setNotAnsweredQuestion(state.notAnsweredQuestion)
            }
            SettingsFragmentVS.Error ->{
                showErrorProgress()
            }
        }
    }
}