package com.wtf.yggd.di.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wtf.yggd.di.scope.BaseScope
import com.wtf.yggd.utils.AppExecutors
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:53
 */
@Module
class AppModule {
    @BaseScope
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @BaseScope
    @Provides
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.Main
    }

    @BaseScope
    @Provides
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
    }

    @BaseScope
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}