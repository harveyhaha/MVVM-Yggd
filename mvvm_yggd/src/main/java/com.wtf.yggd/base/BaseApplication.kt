package com.wtf.yggd.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.wtf.yggd.BuildConfig
import com.wtf.yggd.di.component.BaseAppComponent
import com.wtf.yggd.di.component.DaggerBaseAppComponent
import com.wtf.yggd.logger.CrashReportingTree
import com.wtf.yggd.logger.MyDebugTree
import timber.log.Timber

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-7 上午10:04
 */
open class BaseApplication : Application() {
    lateinit var baseAppComponent: BaseAppComponent
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        initBaseDagger()
        initTimber()
    }

    private fun initBaseDagger() {
        baseAppComponent = DaggerBaseAppComponent.builder().application(this).build()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(MyDebugTree())
        else
            Timber.plant(CrashReportingTree())
    }
}