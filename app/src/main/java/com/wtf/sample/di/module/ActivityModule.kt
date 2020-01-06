package com.wtf.sample.di.module

import com.wtf.sample.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:53
 */
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}