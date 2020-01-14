package com.wtf.yggd.utils

import androidx.lifecycle.LiveData

/**
 * @Description:    A LiveData class that has `null` value.
 * @Author:         harveyhaha
 * @CreateDate:     20-1-14 下午4:07
 */
class AbsentLiveData<T : Any?> private constructor() : LiveData<T>() {
    init {
        // use post instead of set since this can be created on any thread
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}
