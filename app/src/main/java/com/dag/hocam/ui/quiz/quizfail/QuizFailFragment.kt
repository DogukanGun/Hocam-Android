package com.dag.hocam.ui.quiz.quizfail

import android.app.Activity
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.dag.hocam.R
import com.dag.hocam.application.HocamFragment
import com.dag.hocam.application.HocamVS
import com.dag.hocam.application.IntentConstant
import com.dag.hocam.data.quiz.CompleteQuestionRequest
import com.dag.hocam.databinding.FragmentQuizFailBinding
import com.dag.hocam.ui.quiz.QuizFragmentVM
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import javax.inject.Inject

class QuizFailFragment:HocamFragment<QuizFragmentVM,FragmentQuizFailBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_quiz_fail

    private var mInterstitialAd: InterstitialAd? = null

    override fun getLayoutVM(): QuizFragmentVM = quizFragmentVM


    @Inject
    lateinit var quizFragmentVM: QuizFragmentVM

    companion object{
        fun getInstance(completeQuestionRequest: CompleteQuestionRequest):QuizFailFragment{
            return QuizFailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(IntentConstant.COMPETED_QUESTION.name,completeQuestionRequest)
                }
            }
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)
        binding?.repeatIB?.setOnClickListener(repeatButtonListener)
        binding?.closeIB?.setOnClickListener(closeButtonListener)
        arguments?.getParcelable<CompleteQuestionRequest>(IntentConstant.COMPETED_QUESTION.name)?.let{
            showProgress()
            if (it.questionIdList.isNotEmpty()){
                viewModel?.completeQuestions(it)
            }
        }
        return view
    }

    override fun onStateChange(state: HocamVS) {
        when(state){
            QuizFailVS.QuizCompleted->{
                showProgress()
            }
            QuizFailVS.Error->{
                showErrorProgress()
            }
        }
    }

    private val repeatButtonListener = View.OnClickListener {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(requireContext(),"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
                mInterstitialAd?.fullScreenContentCallback = adListener
                mInterstitialAd?.show(getHocamActivity() as Activity)
            }
        })
    }

    val adListener = object: FullScreenContentCallback() {
        override fun onAdDismissedFullScreenContent() {
            mInterstitialAd = null
            finishActivityWithResult(Intent())
        }

        override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
            finishActivity()
        }

        override fun onAdShowedFullScreenContent() {
            mInterstitialAd = null
            finishActivityWithResult(Intent())
        }
    }

    private val closeButtonListener = View.OnClickListener {
        finishActivity()
    }
}