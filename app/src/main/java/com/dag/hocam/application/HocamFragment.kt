package com.dag.hocam.application

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class HocamFragment<VM:HocamVM,DB:ViewDataBinding>:Fragment() {
    abstract fun getLayoutId():Int

    abstract fun getLayoutVM():VM

    protected var viewModel:VM? = null
    protected var binding:DB? = null

    open fun hasSelectButton() = false

    open fun hasBackButton() = false

    open fun hasSettingButton() = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        viewModel.let{ vm->
            if (vm != null) {
                if(!vm.state.hasActiveObservers()){
                    vm.state.observe(this) {
                        onStateChange(it)
                    }
                }

            }
        }
        return binding!!.root
    }
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getLayoutVM()
    }

    private fun getHocamActivity():HocamActivity<*,*>?{
        activity?.let {
            return it as HocamActivity<*,*>
        }
        return null
    }

    fun addFragment(fragment: HocamFragment<*,*>){
        getHocamActivity()?.addFragment(fragment)
    }

    fun startActivity(classAI:Class<*>){
        getHocamActivity()?.startActivity(classAI)
    }

    open fun onStateChange(state:HocamVS){

    }
}