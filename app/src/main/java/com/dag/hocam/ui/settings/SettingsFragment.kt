package com.dag.hocam.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
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
        binding?.settingsListTV?.adapter = SettingsAdapter(requireContext(),SettingsMenu.values().toList())
        return view
    }
}