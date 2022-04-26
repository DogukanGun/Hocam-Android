package com.dag.hocam.application

import android.content.Context
import android.content.Intent
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

    open fun hasCloseButton() = false

    open fun hasNextButton() = false

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

    fun finishActivity(){
        getHocamActivity()?.finish()
    }

    fun startActivityWithArgument(classAI:Class<*>,key:String,value:String){
        getHocamActivity()?.startActivityWithArgument(classAI,key,value)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getLayoutVM()
    }

    fun getHocamActivity():HocamActivity<*,*>?{
        activity?.let {
            return it as HocamActivity<*,*>
        }
        return null
    }

    fun setActionBar(){
        getHocamActivity()?.setActionBar(hasNextButton(),hasSettingButton(),hasCloseButton())
    }

    fun setActionBar(nextButtonState:Boolean,settingsButtonState:Boolean,closeButtonState:Boolean){
        getHocamActivity()?.setActionBar(nextButtonState,settingsButtonState,closeButtonState)
    }

    fun setNextButtonListener(nextButtonListener:View.OnClickListener){
        getHocamActivity()?.setNextButtonListener(nextButtonListener)
    }

    fun addFragment(fragment: HocamFragment<*,*>){
        getHocamActivity()?.addFragment(fragment)
    }

    fun replaceFragment(fragment: HocamFragment<*,*>){
        getHocamActivity()?.replaceFragment(fragment)
    }

    fun startActivity(classAI:Class<*>){
        getHocamActivity()?.startActivity(classAI)
    }

    open fun onStateChange(state:HocamVS){

    }

    fun finishActivityWithResult(intent: Intent){
        getHocamActivity()?.finishActivityWithResult(intent)
    }

    fun showErrorProgress(){
        getHocamActivity()?.showErrorProgress()
    }

    fun showProgress(){
        getHocamActivity()?.showProgress()
    }

    inline fun <T1: Any, T2: Any, R: Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2)->R?): R? {
        return if (p1 != null && p2 != null) block(p1, p2) else null
    }
}