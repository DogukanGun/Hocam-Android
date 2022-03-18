package com.dag.hocam.ui.admin

import android.os.Bundle
import com.dag.hocam.R
import com.dag.hocam.application.HocamActivity
import com.dag.hocam.databinding.ActivityAdminBinding
import com.dag.hocam.ui.settings.SettingsFragment
import javax.inject.Inject

class AdminActivity: HocamActivity<AdminActivityVM,ActivityAdminBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_admin

    override fun getLayoutVM(): AdminActivityVM = adminActivityVM

    override fun hasCloseButton(): Boolean = true

    override fun hasAddButton(): Boolean = false

    @Inject
    lateinit var adminActivityVM: AdminActivityVM

    var currentFragment = AdminFragment()

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
        currentFragment = AdminFragment()
        addFragment(currentFragment)
    }
}