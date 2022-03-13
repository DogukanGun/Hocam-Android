package com.dag.hocam.ui.useroperation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.user.LoginRequest
import com.dag.hocam.data.user.RegisterRequest
import com.dag.hocam.data.user.UserOperationType
import com.dag.hocam.databinding.FragmentUserOperationBinding
import com.dag.hocam.ui.home.HomeActivity
import javax.inject.Inject
import kotlin.math.log

class UserOperationFragment: HocamFragment<UserOperationFragmentVM,FragmentUserOperationBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_user_operation

    override fun getLayoutVM(): UserOperationFragmentVM = userOperationFragmentVM

    @Inject
    lateinit var userOperationFragmentVM: UserOperationFragmentVM

    private var userOperationType = UserOperationType.LOGIN

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding?.apply {
            operationButtonBTN.setOnClickListener(operationDoneButtonListener)
            changeOperationTypeBTN.setOnClickListener(changeOperationButtonListener)
        }
        return view
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            UserOperationFragmentVS.Login ->{
                login()
            }
            UserOperationFragmentVS.StartApplication ->{
                startActivity(HomeActivity::class.java)
            }
        }
    }

    private val changeOperationButtonListener = View.OnClickListener {
        userOperationType = userOperationType.toggle()
        if (userOperationType == UserOperationType.REGISTER){
            binding?.apply {
                titleTV.text = getString(R.string.register)
                changeOperationTypeBTN.text = getString(R.string.change_to_login)
                operationButtonBTN.text = getString(R.string.register)
                emailET.isEnabled = true
            }
        }else if(userOperationType == UserOperationType.LOGIN){
            binding?.apply {
                titleTV.text = getString(R.string.login)
                changeOperationTypeBTN.text = getString(R.string.change_to_register)
                operationButtonBTN.text = getString(R.string.login)
                emailET.isEnabled = false
            }
        }
    }

    private val operationDoneButtonListener = View.OnClickListener {
        if (userOperationType == UserOperationType.LOGIN){
            login()
        }else{
            register()
        }
    }

    private fun login(){
        val loginRequest = LoginRequest(binding?.usernameET?.text.toString(),binding?.passwordET?.text.toString())
        viewModel?.login(loginRequest)
    }

    private fun register(){
        val registerRequest = RegisterRequest(binding?.usernameET?.text.toString(),binding?.passwordET?.text.toString(),
            "", binding?.emailET?.text.toString())
        viewModel?.register(registerRequest)
    }
}