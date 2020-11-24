package com.harveyhaha.sample.app

import android.content.Context
import com.harveyhaha.sample.BuildConfig
import com.harveyhaha.sample.config.ActivityLifecycleCallbacksImpl
import com.harveyhaha.sample.utils.LanguageUtils
import com.harveyhaha.yggd.base.BaseApplication
import com.harveyhaha.yggd.logger.CrashReportingTree
import com.harveyhaha.yggd.logger.MyDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:53
 */
@HiltAndroidApp
open class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        INSTANCE = this
        initActivityLifecycleCallbacks()
        initTimber()
        LanguageUtils.updateAppLanguage(this)
    }

    private fun initActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksImpl())
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(MyDebugTree())
        else
            Timber.plant(CrashReportingTree())
    }

    companion object {
        lateinit var context: Context
        lateinit var INSTANCE: MyApplication
    }
}