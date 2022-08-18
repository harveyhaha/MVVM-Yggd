package com.harveyhaha.yggd.http

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.harveyhaha.yggd.utils.AppExecutors

/**
 * @param ResultType 处理后返回的entity
 * @param RequestType http api 请求返回gson解析的entity
 * @Author:         wutengfei
 * @CreateDate:     2021/1/19 0019 14:57
 */
abstract class NetworkResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        @Suppress("LeakingThis")
        fetchFromNetwork()
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall()
        setValue(Resource.loading(null))
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiSuccessResponse -> {
                    appExecutors.diskIO().execute {
                        val resultData = convertData(processResponse(response))
                        appExecutors.mainThread().execute {
                            setValue(Resource.success(resultData))
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    appExecutors.mainThread().execute {
                        // reload from disk whatever we had
                        setValue(Resource.success(null))
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    setValue(Resource.error(response.errorCode, response.errorMessage))
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract fun convertData(item: RequestType): ResultType

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}
