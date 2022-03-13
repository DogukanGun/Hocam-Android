package com.dag.hocam.di

import android.app.Application
import com.dag.hocam.application.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (AppModule::class),
    (NetworkModule::class),
    (ActivityBuilder::class),
    (ViewModelModule::class)])
interface AppComponent {
    fun inject(app: BaseApplication)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}