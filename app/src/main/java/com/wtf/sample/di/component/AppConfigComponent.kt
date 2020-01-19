package com.wtf.sample.di.component

import com.wtf.sample.di.module.AppConfigModule
import dagger.Component
import okhttp3.Interceptor

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-19 下午4:49
 */
@Component(modules = [AppConfigModule::class])
interface AppConfigComponent {
    fun interceptors(): List<Interceptor>

    @Component.Builder
    interface Builder {

        fun buildAppConfigModule(appConfigModule: AppConfigModule): Builder

        fun build(): AppConfigComponent
    }
}