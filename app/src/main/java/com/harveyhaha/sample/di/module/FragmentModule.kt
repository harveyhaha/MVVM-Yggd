package com.harveyhaha.sample.di.module

import com.harveyhaha.sample.ui.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:53
 */
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): NewsFragment

}