package com.harveyhaha.yggd.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.harveyhaha.yggd.BuildConfig
import com.harveyhaha.yggd.di.component.BaseAppComponent
import com.harveyhaha.yggd.di.component.DaggerBaseAppComponent
import com.harveyhaha.yggd.logger.CrashReportingTree
import com.harveyhaha.yggd.logger.MyDebugTree
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