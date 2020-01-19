package com.wtf.yggd.di.module

import dagger.Module
import okhttp3.Interceptor

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-15 上午9:29
 */
@Module
class GlobalConfigModule constructor(builder: Builder) {
    class Builder {
        lateinit var interceptors: List<Interceptor>

    }
}