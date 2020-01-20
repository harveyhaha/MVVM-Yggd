package com.wtf.sample.di.module

import com.wtf.sample.config.GlobalHttpHandlerImpl
import com.wtf.yggd.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor


/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-19 下午4:09
 */

@Module
class AppConfigModule constructor(var builder: Builder) {
    var globalHttpHandler: GlobalHttpHandlerImpl? = null
    var interceptors: ArrayList<Interceptor>? = null

    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }

    init {
        globalHttpHandler = builder.globalHttpHandler
        interceptors = builder.interceptors
    }

    @AppScope
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor>? {
        return interceptors
    }

    @AppScope
    @Provides
    fun provideGlobalHttpHandler(): GlobalHttpHandlerImpl? {
        return globalHttpHandler
    }

    open class Builder {
        var interceptors: ArrayList<Interceptor>? = null
        var globalHttpHandler: GlobalHttpHandlerImpl? = null

        fun buildAddInterceptor(interceptors: ArrayList<Interceptor>): Builder {
            this.interceptors = interceptors
            return this
        }

        fun buildGlobalHttpHandler(globalHttpHandler: GlobalHttpHandlerImpl): Builder {
            this.globalHttpHandler = globalHttpHandler
            return this
        }

        fun build(): AppConfigModule {
            return AppConfigModule(this)
        }
    }
}