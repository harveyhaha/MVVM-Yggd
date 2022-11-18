package com.harveyhaha.yggd.http

import com.harveyhaha.yggd.http.gsonconvert.ServiceErrorException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * @Description:    A generic class that can provide a resource backed by both the sqlite database and the network.
 * @param ResultType 处理后返回的entity
 * @param RequestType http api 请求返回gson解析的entity
 * @Author:         harveyhaha
 * @CreateDate:     20-1-16 下午5:02
 */
abstract class NetworkBoundResourceFlow<ResultType, RequestType> {
    fun asFlow() = flow<Resource<ResultType>> {
        emit(Resource.loading(null))
        val dbSource = loadFromDb()
        if (shouldFetch(dbSource.first())) {
            var apiResponse: Response<RequestType>? = null
            try {
                apiResponse = createCall()
                val apiResponseBody = apiResponse.body()
                if (apiResponse.isSuccessful) {
                    if (apiResponseBody != null) {
                        saveCallResult(apiResponseBody)
                        emit(Resource.success(loadFromDb().first()))
                    } else {
                        emit(Resource.success(null))
                    }
                } else {
                    onFetchFailed()
                    emit(Resource.error(apiResponse.code(), apiResponse.message()))
                }
            } catch (e: Exception) {
                when (e) {
                    is ServiceErrorException -> {
                        emit(Resource.error(e.errorCode, e.message))
                    }
                    is SocketTimeoutException -> {
                        emit(Resource.error(503, "连接超时"))
                    }
                    is ConnectException -> {
                        emit(Resource.error(504, "网络连接异常"))
                    }
                    else -> {
                        emit(Resource.error(apiResponse?.code() ?: 0, null))
                    }
                }
                e.printStackTrace()
            }
        } else {
            emit(Resource.success(dbSource.first()))
        }
    }

    protected open fun onFetchFailed() {}

    protected open fun processResponse(response: Response<RequestType>) = response.body()

    protected abstract fun saveCallResult(item: RequestType)

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun loadFromDb(): Flow<ResultType>

    protected abstract suspend fun createCall(): Response<RequestType>
}
