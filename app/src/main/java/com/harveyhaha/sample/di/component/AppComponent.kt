/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.harveyhaha.sample.di.component

import com.harveyhaha.sample.app.MyApplication
import com.harveyhaha.sample.di.module.*
import com.harveyhaha.yggd.di.component.BaseAppComponent
import com.harveyhaha.yggd.di.scope.AppScope
import dagger.Component
import dagger.android.AndroidInjectionModule

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:53
 */
@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class
        , ClientModule::class
        , AppConfigModule::class
        , ActivityModule::class
        , FragmentModule::class
        , ViewModelModule::class
    ]
    , dependencies = [
        BaseAppComponent::class
    ]
)
interface AppComponent {
    fun inject(app: MyApplication)
    @Component.Builder
    interface Builder {
        fun baseAppComponent(baseAppComponent: BaseAppComponent): Builder
        fun appConfigModule(appConfigModule: AppConfigModule): Builder
        fun build(): AppComponent
    }
}
