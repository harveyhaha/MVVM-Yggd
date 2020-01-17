package com.wtf.yggd.di.component

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.wtf.yggd.di.module.AppModule
import com.wtf.yggd.utils.AppExecutors
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-15 下午5:08
 */
@Singleton
@Component(
    modules = [AppModule::class]
)
interface BaseAppComponent {
    fun gson(): Gson
    fun coroutineContext(): CoroutineContext
    fun appExecutors(): AppExecutors
    fun application(): Application
    fun context(): Context
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): BaseAppComponent
    }
}