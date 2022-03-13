package com.dag.hocam.di

import android.view.View
import com.dag.hocam.ui.askquestion.AskQuestionActivity
import com.dag.hocam.ui.askquestion.AskQuestionFragment
import com.dag.hocam.ui.home.HomeActivity
import com.dag.hocam.ui.home.HomeFragment
import com.dag.hocam.ui.quiz.QuizActivity
import com.dag.hocam.ui.quiz.QuizFragment
import com.dag.hocam.ui.splash.SplashActivity
import com.dag.hocam.ui.topic.TopicActivity
import com.dag.hocam.ui.topic.TopicFragment
import com.dag.hocam.ui.useroperation.UserOperationActivity
import com.dag.hocam.ui.useroperation.UserOperationFragment
import com.dag.hocam.ui.useroperation.UserOperationFragmentVM
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

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideQuizActivity(): QuizActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideTopicActivity(): TopicActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideUserOperationActivity(): UserOperationActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideQuizFragment(): QuizFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideTopicFragment(): TopicFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideUserOperationFragment(): UserOperationFragment
}