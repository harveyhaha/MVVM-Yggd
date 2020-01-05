package com.wtf.yggd.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by wtf on 1/5/20 11:28 PM.
 */
@Module
abstract class AppModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}