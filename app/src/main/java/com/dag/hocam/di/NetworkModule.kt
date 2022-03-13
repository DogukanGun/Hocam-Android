package com.dag.hocam.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.dag.hocam.application.Constant
import com.dag.hocam.network.ApiServiceImpl
import com.dag.hocam.network.ApiSource
import com.dag.hocam.retrofit.ApiLogger
import com.dag.hocam.retrofit.HttpRetrofitInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideApiSource(retrofit: Retrofit): ApiSource = ApiServiceImpl(retrofit)

    @Provides
    fun provideRetrofit(context: Context): Retrofit {
        val logger = HttpLoggingInterceptor(ApiLogger())
        logger.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = HttpRetrofitInterceptor()
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .addInterceptor(logger)
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constant.url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

}