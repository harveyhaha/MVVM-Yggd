package com.harveyhaha.yggd.logger

import android.util.Log
import timber.log.Timber

/**
 *
 * @Description: 可做崩溃日志上传
 * @Author:         harveyhaha
 * @CreateDate:     20-1-7 下午2:41
 */
class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (t != null) {
            if (priority == Log.ERROR) {
                t.printStackTrace()
            } else if (priority == Log.WARN) {
                t.printStackTrace()
            }
        }
    }
}