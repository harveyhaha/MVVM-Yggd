package com.wtf.sample.app

import android.app.Application
import android.content.Context
import com.wtf.sample.di.component.DaggerAppComponent
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
open class MyApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initDagger()
    }

    open fun initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    companion object {
        lateinit var context: Context
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}