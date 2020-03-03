package com.harveyhaha.sample.di.module

import com.harveyhaha.sample.ui.MainActivity
import com.harveyhaha.sample.ui.SplashActivity
import com.harveyhaha.sample.ui.login.LoginActivity
import com.harveyhaha.sample.ui.setting.SettingActivity
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
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeSettingActivity(): SettingActivity
}