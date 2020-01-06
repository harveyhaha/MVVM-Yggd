package com.wtf.sample.di.module

import androidx.lifecycle.ViewModelProvider
import com.wtf.yggd.base.ViewModelFactory
import dagger.Binds
import dagger.Module


/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:53
 */
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?
}
