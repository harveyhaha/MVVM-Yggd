package com.harveyhaha.yggd.http

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.harveyhaha.yggd.utils.AppExecutors

/**
 * @Description:    只进行网络请求,不做本地数据库处理
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 * @Author:         harveyhaha
 * @CreateDate:     20-1-16 下午5:02
 */
abstract class NetworkOnlyFetchResource<RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<RequestType>>()

    init {
        result.value = Resource.loading(null)
        @Suppress("LeakingThis")
        fetchFromNetwork()
    }

    @MainThread
    private fun setValue(newValue: Resource<RequestType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall()
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiSuccessResponse -> {
                    appExecutors.diskIO().execute {
                        appExecutors.mainThread().execute {
                            setValue(Resource.success(processResponse(response)))
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    appExecutors.mainThread().execute {
                        setValue(Resource.success(null))
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    setValue(Resource.error(response.errorMessage, null))
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<RequestType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}
