package com.wtf.sample.di.module.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wtf.sample.viewmodels.MainViewModel
import com.wtf.yggd.di.ViewModelFactory
import com.wtf.yggd.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:53
 */
@Module
abstract class TestViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(model: MainViewModel): ViewModel
}