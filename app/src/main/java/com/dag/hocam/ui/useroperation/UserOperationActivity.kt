package com.dag.hocam.ui.useroperation

import android.os.Bundle
import com.dag.hocam.R
import com.dag.hocam.application.HocamActivity
import com.dag.hocam.databinding.ActivityUserOperationBinding
import javax.inject.Inject

class UserOperationActivity: HocamActivity<UserOperationActivityVM,ActivityUserOperationBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_user_operation

    override fun getLayoutVM(): UserOperationActivityVM = userOperationActivityVM

    @Inject
    lateinit var userOperationActivityVM: UserOperationActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(UserOperationFragment())
    }
}