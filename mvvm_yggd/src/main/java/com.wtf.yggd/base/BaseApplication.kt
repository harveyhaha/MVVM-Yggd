package com.wtf.yggd.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.wtf.yggd.BuildConfig
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

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(MyDebugTree())
        else
            Timber.plant(CrashReportingTree())
    }
}