package com.dag.hocam.di

import com.dag.hocam.ui.admin.*
import com.dag.hocam.ui.admin.question.AdminAddQuestionFragment
import com.dag.hocam.ui.admin.quiz.AdminAddQuizFragment
import com.dag.hocam.ui.admin.topic.AddTopicFragment
import com.dag.hocam.ui.askquestion.AskQuestionActivity
import com.dag.hocam.ui.askquestion.AskQuestionFragment
import com.dag.hocam.ui.home.HomeActivity
import com.dag.hocam.ui.home.HomeFragment
import com.dag.hocam.ui.quiz.QuizActivity
import com.dag.hocam.ui.quiz.QuizFragment
import com.dag.hocam.ui.quiz.quizfail.QuizFailActivity
import com.dag.hocam.ui.quiz.quizfail.QuizFailFragment
import com.dag.hocam.ui.quiz.quizresult.QuizResultFragment
import com.dag.hocam.ui.settings.SettingsActivity
import com.dag.hocam.ui.settings.SettingsFragment
import com.dag.hocam.ui.settings.SettingsQuizNotAnsweredQuestion
import com.dag.hocam.ui.splash.SplashActivity
import com.dag.hocam.ui.topic.TopicActivity
import com.dag.hocam.ui.topic.TopicFragment
import com.dag.hocam.ui.useroperation.UserOperationActivity
import com.dag.hocam.ui.useroperation.UserOperationFragment
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

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideAdminActivity(): AdminActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideAdminFragment(): AdminFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideAdminAddQuestionFragment(): AdminAddQuestionFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideQuizResultFragment(): QuizResultFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideSettingsActivity(): SettingsActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideSettingsQuizNotAnsweredQuestion(): SettingsQuizNotAnsweredQuestion

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideAdminAddQuizFragment(): AdminAddQuizFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideAddTopicFragment(): AddTopicFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideQuizFailFragment(): QuizFailFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun provideQuizFailActivity(): QuizFailActivity
}