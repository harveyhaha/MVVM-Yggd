package com.harveyhaha.yggd.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*
import kotlin.system.exitProcess

/**
 *
 * @Description: 管理所有Activity
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午5:20
 */
class AppManager private constructor() {
    private val activityStack: Stack<Activity> = Stack()

    companion object {
        val instance: AppManager by lazy { AppManager() }
    }

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * 移除指定的Activity
     */
    fun removeActivity(activity: Activity) {
        activityStack.remove(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity {
        return activityStack.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity) {
        if (!activity.isFinishing) {
            activity.finish()
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
                break
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        for (activity in activityStack) {
            finishActivity(activity)
        }
        activityStack.clear()
    }

    /**
     * 获取指定的Activity
     *
     * @author kymjs
     */
    fun getActivity(cls: Class<*>): Activity? {
        for (activity in activityStack) {
            if (activity.javaClass == cls) {
                return activity
            }
        }
        return null
    }

    /**
     * 退出应用程序
     */
    fun AppExit(context: Context) {
        try {
            finishAllActivity()
            val activityManager =
                context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityManager.killBackgroundProcesses(context.packageName)
            exitProcess(0)
        } catch (e: Exception) {
            activityStack.clear()
            e.printStackTrace()
        }
    }
}