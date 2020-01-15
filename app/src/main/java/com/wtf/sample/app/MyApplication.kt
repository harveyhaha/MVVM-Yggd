package com.wtf.sample.app

import android.content.Context
import com.wtf.sample.di.component.DaggerTestAppComponent
import com.wtf.yggd.base.BaseApplication
import com.wtf.yggd.di.component.DaggerBaseAppComponent
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
        initDagger()
    }

    private fun initDagger() {
//        DaggerAppComponent.builder().application(this).build().inject(this)
        val baseAppComponent = DaggerBaseAppComponent.builder().application(this).build()
        DaggerTestAppComponent.builder().baseAppComponent(baseAppComponent).build().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    companion object {
        lateinit var context: Context
        lateinit var INSTANCE: BaseApplication
    }
}