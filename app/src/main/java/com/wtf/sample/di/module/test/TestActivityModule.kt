package com.wtf.sample.di.module.test

import com.wtf.sample.ui.test.Dagger2TestActivity
import com.wtf.sample.ui.test.Dagger2TestBaseActivity
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