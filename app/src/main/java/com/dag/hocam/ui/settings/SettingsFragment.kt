package com.dag.hocam.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.settings.SettingsMenu
import com.dag.hocam.databinding.FragmentSettingsBinding
import javax.inject.Inject

class SettingsFragment: HocamFragment<SettingsFragmentVM,FragmentSettingsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_settings

    override fun getLayoutVM(): SettingsFragmentVM = settingsFragmentVM

    @Inject
    lateinit var settingsFragmentVM: SettingsFragmentVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding?.settingsListTV?.apply {
            adapter = SettingsAdapter(requireContext(),SettingsMenu.values().toList()).also {
                it.listener = settingsAdapterListener
            }
            layoutManager = LinearLayoutManager(requireContext())
        }
        return view
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            is SettingsFragmentVS.SetQuizzes ->{
                showProgress()
                val newList = state.quizzes.map { "${it.id}"}
                binding?.settingsListTV?.adapter = SettingsQuizAdapter(newList).also {
                    it.settingsListener = settingsQuizAdapterListener
                }
            }
            is SettingsFragmentVS.SetSolvedQuizzes ->{
                showProgress()
                val newList = state.solvedQuizzes.map { "${it.quizName} => ${it.point}"}
                binding?.settingsListTV?.adapter = SettingsQuizAdapter(newList)
            }
            SettingsFragmentVS.Error ->{
                showErrorProgress()
            }
        }
    }

    private fun solvedQuizzesShow(){
        showProgress()
        viewModel?.getSolvedQuizzes()
    }

    private fun showNotAnsweredQuestions(){
        showProgress()
        viewModel?.getSolvedQuestion()
    }

    private val settingsQuizAdapterListener = object :SettingsQuizAdapter.SettingsQuizAdapterListener{
        override fun cellClicked(id: Int) {
            addFragment(SettingsQuizNotAnsweredQuestion.getInstance(id))
        }
    }

    private val settingsAdapterListener = object :SettingsAdapter.SettingsAdapterListener{
        override fun itemSelected(settingsMenu: SettingsMenu) {
            when(settingsMenu){
                SettingsMenu.AnswerQuestions ->{
                    showNotAnsweredQuestions()
                }
                SettingsMenu.SolvedQuizzes ->{
                    solvedQuizzesShow()
                }
            }
        }
    }
}