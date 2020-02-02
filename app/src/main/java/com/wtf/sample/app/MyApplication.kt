package com.wtf.sample.app

import android.content.Context
import com.wtf.sample.config.ActivityLifecycleCallbacksImpl
import com.wtf.sample.config.GlobalHttpHandlerImpl
import com.wtf.sample.di.component.DaggerAppComponent
import com.wtf.sample.di.module.AppConfigModule
import com.wtf.sample.utils.LanguageUtils
import com.wtf.yggd.base.BaseApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
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

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    companion object {
        lateinit var context: Context
        lateinit var INSTANCE: MyApplication
    }
}