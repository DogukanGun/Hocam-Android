package com.dag.hocam.ui.admin

import android.os.Bundle
import com.dag.hocam.R
import com.dag.hocam.application.HocamActivity
import com.dag.hocam.databinding.ActivityAdminBinding
import javax.inject.Inject

class AdminActivity: HocamActivity<AdminActivityVM,ActivityAdminBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_admin

    override fun getLayoutVM(): AdminActivityVM = adminActivityVM

    @Inject
    lateinit var adminActivityVM: AdminActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(AdminFragment())
    }
}