package com.harveyhaha.sample.app

import android.content.Context
import com.harveyhaha.sample.BuildConfig
import com.harveyhaha.sample.config.ActivityLifecycleCallbacksImpl
import com.harveyhaha.sample.config.GlobalHttpHandlerImpl
import com.harveyhaha.sample.di.component.DaggerAppComponent
import com.harveyhaha.sample.di.module.AppConfigModule
import com.harveyhaha.sample.utils.LanguageUtils
import com.harveyhaha.yggd.base.BaseApplication
import com.harveyhaha.yggd.logger.CrashReportingTree
import com.harveyhaha.yggd.logger.MyDebugTree
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:53
 */
open class MyApplication : BaseApplication(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        INSTANCE = this
        initActivityLifecycleCallbacks()
        initDagger()
        initTimber()
        LanguageUtils.updateAppLanguage(this)
    }

    private fun initActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksImpl())
    }

    private fun initDagger() {
        val appConfigModule = AppConfigModule.Builder()
            .buildGlobalHttpHandler(GlobalHttpHandlerImpl())
            .build()
        DaggerAppComponent.builder()
            .baseAppComponent(baseAppComponent)
            .appConfigModule(appConfigModule)
            .build()
            .inject(this)
        //用于测试dagger
//        DaggerTestAppComponent.builder().baseAppComponent(baseAppComponent).build().inject(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(MyDebugTree())
        else
            Timber.plant(CrashReportingTree())
    }
    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    companion object {
        lateinit var context: Context
        lateinit var INSTANCE: MyApplication
    }
}