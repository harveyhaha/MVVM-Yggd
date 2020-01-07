package com.wtf.sample.di.module

import androidx.lifecycle.ViewModelProvider
import com.wtf.sample.ui.ViewModelFactory
import com.wtf.sample.viewmodels.MainViewModel
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
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    //    @Binds
//    @IntoMap
//    @ViewModelKey(BaseViewModel::class)
//    abstract fun bindBaseViewModel(model: BaseViewModel): BaseViewModel
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(model: MainViewModel): MainViewModel

}
