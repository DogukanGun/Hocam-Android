package com.dag.hocam.di

import com.dag.hocam.ui.askquestion.AskQuestionActivityVM
import com.dag.hocam.ui.askquestion.AskQuestionFragmentVM
import com.dag.hocam.ui.home.HomeActivityVM
import com.dag.hocam.ui.home.HomeFragmentVM
import com.dag.hocam.ui.splash.SplashVM
import dagger.Module
import dagger.Provides


@Module
class ViewModelModule {

    @Provides
    fun provideSplashVM() = SplashVM()

    @Provides
    fun provideHomeActivityVM() = HomeActivityVM()

    @Provides
    fun provideAskQuestionActivityVM() = AskQuestionActivityVM()

    @Provides
    fun provideAskQuestionFragmentVM() = AskQuestionFragmentVM()

    @Provides
    fun provideHomeFragmentVM() = HomeFragmentVM()
}