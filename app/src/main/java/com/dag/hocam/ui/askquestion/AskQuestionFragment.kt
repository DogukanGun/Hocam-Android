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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.questionfromuser.AddQuestionFromUserRequest
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
    private var imageBase64Format:String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)
        registerActivityResultLauncher()
        binding?.questionIV?.setOnClickListener(imageClickListener)
        binding?.submitBTN?.setOnClickListener {
            binding?.senderEmailET?.text?.toString()
                ?.let { it1 -> AddQuestionFromUserRequest(imageBase64Format, it1) }?.let {
                    showProgress()
                    viewModel?.uploadQuestion(it)
                }
        }
        return view
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            AskFragmentVS.QuestionSent ->{
                showProgress()
            }
            AskFragmentVS.Error ->{
                showErrorProgress()
            }
        }
    }

    fun convertToBase64(bitmap: Bitmap):String{
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private val imageClickListener = View.OnClickListener {
        getContent.launch("image/*")
    }

    private fun registerActivityResultLauncher(){
        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            Glide.with(requireContext())
                .asBitmap()
                .load(uri)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        TODO("Not yet implemented")
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        resource?.let {
                            imageBase64Format = convertToBase64(it)
                            binding?.questionIV?.setImageBitmap(it)
                        }
                        return true
                    }

                }).submit()
            binding?.uploadTextTV?.visibility = View.GONE
        }
    }
}