package com.dag.hocam.application

import android.app.Activity
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dag.hocam.BR
import com.dag.hocam.R
import com.dag.hocam.data.animation.AnimationType
import com.dag.hocam.data.session.SessionKey
import com.dag.hocam.ui.admin.AdminActivity
import com.dag.hocam.ui.settings.SettingsActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class HocamActivity<VM:HocamVM,DB: ViewDataBinding>:AppCompatActivity() {

    abstract fun getLayoutId():Int

    abstract fun getLayoutVM():VM

    private fun getContainerId() = R.id.container

    open fun hasSelectButton() = false

    open fun hasBackButton() = false

    open fun hasSettingButton() = false

    open fun hasCloseButton() = false

    open fun hasAddButton() = true

    open fun hasNextButton() = false

    protected var viewModel:VM? = null
    protected var binding:DB? = null
    private var nextButtonListener:View.OnClickListener? = null
    private var progressShowing = false
    private var progressDialog:HocamProgressDialog? = null

    @Inject
    lateinit var sessionManager: HocamSessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Constant.loggedIn && Constant.token.length < 2){
            finishAndRemoveTask()
        }
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

    fun setActionBar(nextButtonState:Boolean,settingsButtonState:Boolean,closeButtonState:Boolean){
        val addButton = findViewById<ImageButton>(R.id.addBTN)
        val settingButton = findViewById<ImageButton>(R.id.settingsBTN)
        val nextButton = findViewById<ImageButton>(R.id.nextBTN)
        val closeButton = findViewById<ImageButton>(R.id.closeBTN)

        sessionManager.getData<String>(SessionKey.USERTYPE.name)?.let {
            if(it.lowercase() == "admin" && !nextButtonState && hasAddButton()){
                addButton.visibility = View.VISIBLE
                addButton.setOnClickListener {
                    startActivity(AdminActivity::class.java)
                }
            }
        }
        settingButton.visibility = if (settingsButtonState) View.VISIBLE else View.GONE
        nextButton.visibility = if (nextButtonState) View.VISIBLE else View.GONE
        closeButton.visibility = if (closeButtonState && !settingsButtonState) View.VISIBLE else View.GONE
        nextButton.setOnClickListener(if (nextButtonState) nextButtonListener else null)
        settingButton.setOnClickListener(if (settingsButtonState) settingButtonListener else null)
        closeButton.setOnClickListener(if (closeButtonState && !settingsButtonState) closeButtonListener else null)

    }

    fun setActionBar(){
        setActionBar(hasNextButton(),hasSettingButton(),hasCloseButton())
    }

    private var closeButtonListener = View.OnClickListener {
        finish()
    }

    private var settingButtonListener = View.OnClickListener{
        startActivity(SettingsActivity::class.java)
    }

    fun setNextButtonListener(nextButtonListener:View.OnClickListener){
        this.nextButtonListener = nextButtonListener
    }

    fun addFragment(fragment: HocamFragment<*,*>){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(getContainerId(),fragment,null)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun replaceFragment(fragment: HocamFragment<*,*>){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        getCurrentFragment()?.let { fragmentTransaction.remove(it) }
        fragmentTransaction.replace(getContainerId(),fragment,null)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun finishActivityWithResult(intent: Intent){
        setResult(Activity.RESULT_OK,intent)
        finish()
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

    fun showError(){

    }

    fun showErrorProgress(){
        progressDialog?.dismiss()
        progressDialog = HocamProgressDialog(this,AnimationType.ERROR)
        progressDialog?.show()
        progressDialog?.listener = object :HocamProgressDialog.HocamAnimationListener{
            override fun finishAnimation() {
                progressDialog?.dismiss()
            }
        }
    }

    fun showProgress(){
        if (progressShowing){
            progressShowing = false
            progressDialog?.dismiss()
        }else{
            progressShowing = true
            progressDialog = HocamProgressDialog(this)
            progressDialog?.show()
        }
    }


    inline fun <T1: Any, T2: Any, R: Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2)->R?): R? {
        return if (p1 != null && p2 != null) block(p1, p2) else null
    }
}