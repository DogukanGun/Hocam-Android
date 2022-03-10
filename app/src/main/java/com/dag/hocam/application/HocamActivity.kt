package com.dag.hocam.application

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dag.hocam.BR
import com.dag.hocam.R
import dagger.android.AndroidInjection

abstract class HocamActivity<VM:HocamVM,DB: ViewDataBinding>:AppCompatActivity() {

    abstract fun getLayoutId():Int

    abstract fun getLayoutVM():VM

    private fun getContainerId() = R.id.container

    open fun hasSelectButton() = false

    open fun hasBackButton() = false

    open fun hasSettingButton() = false

    protected var viewModel:VM? = null
    protected var binding:DB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = getLayoutVM()
        binding = DataBindingUtil.setContentView(this,getLayoutId())
        (viewModel as HocamVM).let { hocamVM ->
            if (!hocamVM.state.hasActiveObservers()){
                hocamVM.state.observe(this){
                    onStateChange(it)
                }
            }
        }
    }

    fun addFragment(fragment: HocamFragment<*,*>){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(getContainerId(),fragment,null)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun startActivity(classAI:Class<*>){
        val intent = Intent(this,classAI)
        startActivity(intent)
    }

    open fun onStateChange(state:HocamVS){

    }
}