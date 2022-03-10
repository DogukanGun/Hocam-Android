package com.dag.hocam.di

import android.view.View
import com.dag.hocam.ui.askquestion.AskQuestionActivity
import com.dag.hocam.ui.askquestion.AskQuestionFragment
import com.dag.hocam.ui.home.HomeActivity
import com.dag.hocam.ui.home.HomeFragment
import com.dag.hocam.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideAskQuestionFragment(): AskQuestionFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideAskQuestionActivity(): AskQuestionActivity
}