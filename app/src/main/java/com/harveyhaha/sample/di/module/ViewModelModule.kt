package com.harveyhaha.sample.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harveyhaha.sample.viewmodels.LoginViewModel
import com.harveyhaha.sample.viewmodels.MainViewModel
import com.harveyhaha.sample.viewmodels.NewsViewModel
import com.harveyhaha.sample.viewmodels.SplashModel
import com.harveyhaha.yggd.di.ViewModelFactory
import com.harveyhaha.yggd.di.ViewModelKey
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

    @Binds
    @IntoMap
    @ViewModelKey(SplashModel::class)
    abstract fun bindSplashModel(model: SplashModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(model: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(model: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(model: NewsViewModel): ViewModel

}
