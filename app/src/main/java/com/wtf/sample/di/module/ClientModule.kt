package com.wtf.sample.di.module

import com.wtf.sample.api.HttpServiceApi
import com.wtf.sample.config.BASE_URL
import com.wtf.sample.http.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-10 下午5:11
 */
@Module
class ClientModule {
    @Singleton
    @Provides
    fun provideHttpService(): HttpServiceApi {
        val httpClientBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        httpClientBuilder.addInterceptor(httpLoggingInterceptor)
        return Retrofit.Builder()
            .client(
                httpClientBuilder
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .build()
            )
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(HttpServiceApi::class.java)
    }
}