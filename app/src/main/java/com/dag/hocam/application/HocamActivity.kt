package com.dag.hocam.application

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dag.hocam.BR
import com.dag.hocam.R
import com.dag.hocam.data.session.SessionKey
import com.dag.hocam.ui.admin.AdminActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class HocamActivity<VM:HocamVM,DB: ViewDataBinding>:AppCompatActivity() {

    abstract fun getLayoutId():Int

    abstract fun getLayoutVM():VM

    private fun getContainerId() = R.id.container

    open fun hasSelectButton() = false

    open fun hasBackButton() = false

    open fun hasSettingButton() = false

    protected var viewModel:VM? = null
    protected var binding:DB? = null

    @Inject
    lateinit var sessionManager: HocamSessionManager

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
    override fun onBackPressed() {
        val result = back()
        if (result == -1){
            super.onBackPressed()
        }
    }

    fun getCurrentFragment():HocamFragment<*,*>?{
        val currentFragment = supportFragmentManager.findFragmentById(getContainerId())
        if (currentFragment != null){
            return  currentFragment as HocamFragment<*, *>
        }
        return null
    }

    private fun back():Int{
        val currentFragment = getCurrentFragment()
        if (currentFragment != null){
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.remove(currentFragment)
            fragmentTransaction.commitAllowingStateLoss()
            supportFragmentManager.popBackStackImmediate()
            if (supportFragmentManager.backStackEntryCount == 0) {
                return -1
            }
            return 0
        }else{
            return -1
        }

    }

    fun setActionBar(){
        val addButton = findViewById<ImageButton>(R.id.addBTN)
        sessionManager.getData<String>(SessionKey.USERTYPE.name)?.let {
            if(it.lowercase().equals("admin")){
                addButton.visibility = View.VISIBLE
                addButton.setOnClickListener {
                    startActivity(AdminActivity::class.java)
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

    fun startActivityWithArgument(classAI:Class<*>,key:String,value:String){
        val intent = Intent(this,classAI)
        intent.putExtra(key,value)
        startActivity(intent)
    }

    open fun onStateChange(state:HocamVS){

    }
}