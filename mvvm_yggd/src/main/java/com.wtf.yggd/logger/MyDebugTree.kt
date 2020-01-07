package com.wtf.yggd.logger

import android.util.Log
import timber.log.Timber

/**
 *
 * @Description:自定义Tree显示行号,可快速定位
 * @Author:         harveyhaha
 * @CreateDate:     20-1-7 下午2:44
 */
class MyDebugTree : Timber.DebugTree() {
    private val MAX_LOG_LENGTH = 4000
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val stackTrace = Thread.currentThread().stackTrace
        val index = 7
        val className = stackTrace[index].fileName
        val lineNumber = stackTrace[index].lineNumber
        val messageBuilder = StringBuilder()
        messageBuilder.append("[($className:$lineNumber)] ")
        messageBuilder.append(message)
        if (message.length < MAX_LOG_LENGTH) {
            if (priority == Log.ASSERT) {
                Log.wtf(tag, messageBuilder.toString())
            } else {
                Log.println(priority, tag, messageBuilder.toString())
            }
            return
        }
        // Split by line, then ensure each line can fit into Log's maximum length.
        var i = 0
        val length = messageBuilder.toString().length
        while (i < length) {
            var newline = messageBuilder.toString().indexOf('\n', i)
            newline = if (newline != -1) newline else length
            do {
                val end = Math.min(newline, i + MAX_LOG_LENGTH)
                val part = messageBuilder.toString().substring(i, end)
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, part)
                } else {
                    Log.println(priority, tag, part)
                }
                i = end
            } while (i < newline)
            i++
        }
    }
}