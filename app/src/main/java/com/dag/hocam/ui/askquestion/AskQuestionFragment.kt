package com.dag.hocam.ui.askquestion

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.databinding.FragmentAskquestionBinding
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import javax.inject.Inject


class AskQuestionFragment: HocamFragment<AskQuestionFragmentVM,FragmentAskquestionBinding>(){
    override fun getLayoutId(): Int = R.layout.fragment_askquestion

    override fun getLayoutVM(): AskQuestionFragmentVM = askQuestionFragmentVM

    @Inject
    lateinit var askQuestionFragmentVM: AskQuestionFragmentVM

    lateinit var getContent : ActivityResultLauncher<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)
        registerActivityResultLauncher()
        binding?.imageViewIV?.setOnClickListener(imageClickListener)
        return view
    }

    private val imageClickListener = View.OnClickListener {
        getContent.launch("image/*")
    }

    private fun registerActivityResultLauncher(){
        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            Picasso.get().load(uri).into(binding?.imageViewIV)
            binding?.uploadTextTV?.visibility = View.GONE
        }
    }
}