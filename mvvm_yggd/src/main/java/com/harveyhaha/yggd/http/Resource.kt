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

package com.harveyhaha.yggd.http

import com.harveyhaha.yggd.http.Status.Companion.ERROR
import com.harveyhaha.yggd.http.Status.Companion.LOADING
import com.harveyhaha.yggd.http.Status.Companion.SUCCESS

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
class Resource<out T>(@Status val status: Int, val data: T?, var code: Int? = 0, val message: String?) {
    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(SUCCESS, data, null, null)
        }

        fun <T> error(msg: String? = null, data: T? = null): Resource<T> {
            return Resource(ERROR, data, null, msg)
        }

        fun <T> error(errorCode: Int, msg: String? = null, data: T? = null): Resource<T> {
            return Resource(ERROR, data, errorCode, msg)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(LOADING, data, null, null)
        }

        fun <T> loading(message: String?): Resource<T> {
            return Resource(LOADING, null, -1, message)
        }
    }
}
