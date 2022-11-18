package com.harveyhaha.yggd.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

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
    }
}