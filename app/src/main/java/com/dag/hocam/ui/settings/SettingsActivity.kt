package com.dag.hocam.ui.settings

import android.os.Bundle
import com.dag.hocam.R
import com.dag.hocam.application.HocamActivity
import com.dag.hocam.databinding.ActivitySettingsBinding
import javax.inject.Inject

class SettingsActivity: HocamActivity<SettingsActivityVM,ActivitySettingsBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_settings

    override fun getLayoutVM(): SettingsActivityVM = settingsActivityVM

    override fun hasCloseButton(): Boolean = true

    @Inject
    lateinit var settingsActivityVM: SettingsActivityVM

    var currentFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBar()
        addFragment(currentFragment)
    }

    override fun onBackPressed() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(currentFragment)
        fragmentTransaction.commitAllowingStateLoss()
        supportFragmentManager.popBackStackImmediate()
        currentFragment = SettingsFragment()
        addFragment(currentFragment)
    }
}