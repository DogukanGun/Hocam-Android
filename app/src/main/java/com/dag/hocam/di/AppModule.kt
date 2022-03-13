package com.dag.hocam.di

import android.app.Application
import com.dag.hocam.application.HocamSessionManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application) = application.applicationContext

    @Singleton
    @Provides
    fun provideSession() = HocamSessionManager()
}