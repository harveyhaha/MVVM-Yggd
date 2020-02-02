package com.wtf.sample.config

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.wtf.sample.utils.LanguageUtils
import timber.log.Timber

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     2/2/20 7:36 PM
 */
open class ActivityLifecycleCallbacksImpl : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Timber.i("onActivityCreate %s", activity.localClassName)
        LanguageUtils.updateAppLanguage(activity)
    }

    override fun onActivityResumed(activity: Activity) {
    }
}