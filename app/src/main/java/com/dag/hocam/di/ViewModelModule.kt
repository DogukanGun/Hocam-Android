package com.dag.hocam.di

import com.dag.hocam.network.ApiSource
import com.dag.hocam.ui.askquestion.AskQuestionActivityVM
import com.dag.hocam.ui.askquestion.AskQuestionFragmentVM
import com.dag.hocam.ui.home.HomeActivityVM
import com.dag.hocam.ui.home.HomeFragmentVM
import com.dag.hocam.ui.quiz.QuizActivityVM
import com.dag.hocam.ui.quiz.QuizFragmentVM
import com.dag.hocam.ui.splash.SplashVM
import com.dag.hocam.ui.topic.TopicActivityVM
import com.dag.hocam.ui.topic.TopicFragmentVM
import com.dag.hocam.ui.useroperation.UserOperationActivityVM
import com.dag.hocam.ui.useroperation.UserOperationFragmentVM
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

    @Provides
    fun provideQuizActivityVM() = QuizActivityVM()

    @Provides
    fun provideQuizFragmentVM() = QuizFragmentVM()

    @Provides
    fun provideTopicActivityVM() = TopicActivityVM()

    @Provides
    fun provideTopicFragmentVM() = TopicFragmentVM()

    @Provides
    fun provideUserOperationActivityVM() = UserOperationActivityVM()

    @Provides
    fun provideUserOperationFragmentVM(apiSource: ApiSource) = UserOperationFragmentVM(apiSource)
}