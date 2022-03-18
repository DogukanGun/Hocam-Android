package com.dag.hocam.ui.settings

import android.os.Bundle
import com.dag.hocam.R
import com.dag.hocam.application.HocamActivity
import com.dag.hocam.databinding.ActivitySettingsBinding
import javax.inject.Inject

class SettingsActivity: HocamActivity<SettingsActivityVM,ActivitySettingsBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_settings

    override fun getLayoutVM(): SettingsActivityVM = settingsActivityVM

    @Inject
    lateinit var settingsActivityVM: SettingsActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(SettingsFragment())
    }
}