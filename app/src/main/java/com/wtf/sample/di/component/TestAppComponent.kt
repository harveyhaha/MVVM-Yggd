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

package com.wtf.sample.di.component

import com.wtf.sample.app.MyApplication
import com.wtf.sample.di.module.test.TestActivityModule
import com.wtf.sample.di.module.test.TestClientModule
import com.wtf.sample.di.module.test.TestViewModelModule
import com.wtf.yggd.di.component.BaseAppComponent
import com.wtf.yggd.di.scope.AppScope
import dagger.Component
import dagger.android.AndroidInjectionModule

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:53
 */
//@AppScope
//@Component(
//    modules = [
//        AndroidInjectionModule::class,
////        AppModule::class,
////        GlobalConfigModule::class,
//        TestClientModule::class,
//        TestActivityModule::class,
////        FragmentModule::class,
//        TestViewModelModule::class
//    ], dependencies = [BaseAppComponent::class]
//)
interface TestAppComponent {
    fun inject(app: MyApplication)
}
