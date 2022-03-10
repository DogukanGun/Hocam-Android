package com.dag.hocam.ui.askquestion

import android.os.Bundle
import com.dag.hocam.R
import com.dag.hocam.application.HocamActivity
import com.dag.hocam.databinding.ActivityAskquestionBinding
import javax.inject.Inject

class AskQuestionActivity: HocamActivity<AskQuestionActivityVM,ActivityAskquestionBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_askquestion

    override fun getLayoutVM(): AskQuestionActivityVM = askQuestionActivityVM

    @Inject
    lateinit var askQuestionActivityVM: AskQuestionActivityVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(AskQuestionFragment())
    }
}