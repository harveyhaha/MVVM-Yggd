package com.wtf.sample.di.module

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
    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }

    //    @ConfigScope
    @Provides
    fun provideInterceptors(): List<Interceptor> {
        return builder.interceptors
    }

    open class Builder {
        var interceptors: ArrayList<Interceptor> = ArrayList()

        fun buildAddInterceptor(interceptor: Interceptor): Builder {
            this.interceptors.add(interceptor)
            return this
        }

        fun build(): AppConfigModule {
            return AppConfigModule(this)
        }
    }
}