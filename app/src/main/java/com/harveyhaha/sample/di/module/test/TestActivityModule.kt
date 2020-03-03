package com.harveyhaha.sample.di.module.test

import com.harveyhaha.sample.ui.test.Dagger2TestActivity
import com.harveyhaha.sample.ui.test.Dagger2TestBaseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:53
 */
@Module
abstract class TestActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeDagger2TestActivity(): Dagger2TestActivity

    @ContributesAndroidInjector
    abstract fun contributeDagger2TestBaseActivity(): Dagger2TestBaseActivity
}