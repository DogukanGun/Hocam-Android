package com.dag.hocam.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.databinding.FragmentHomeBinding
import com.dag.hocam.ui.askquestion.AskQuestionActivity
import javax.inject.Inject

class HomeFragment: HocamFragment<HomeFragmentVM,FragmentHomeBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun getLayoutVM(): HomeFragmentVM = homeFragmentVM

    @Inject
    lateinit var homeFragmentVM: HomeFragmentVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding?.sendQuestionBTN?.setOnClickListener(sendQuestionButtonListener)
        return view
    }

    private var sendQuestionButtonListener = View.OnClickListener {
        startActivity(AskQuestionActivity::class.java)
    }
}