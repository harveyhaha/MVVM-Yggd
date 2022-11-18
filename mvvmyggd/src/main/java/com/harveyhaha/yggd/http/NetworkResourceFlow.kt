package com.harveyhaha.yggd.http

import com.harveyhaha.yggd.http.gsonconvert.ServiceErrorException
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * @param ResultType 处理后返回的entity
 * @param RequestType http api 请求返回gson解析的entity
 * @Author:         wutengfei
 * @CreateDate:     2021/1/19 0019 14:57
 */
abstract class NetworkResourceFlow<ResultType, RequestType> constructor() {

    fun asFlow() = flow<Resource<ResultType>> {
        emit(Resource.loading(null))
        var apiResponse: Response<RequestType>? = null
        try {
            apiResponse = createCall()
            val apiResponseBody = apiResponse.body()
            if (apiResponse.isSuccessful) {
                if (apiResponseBody != null) {
                    val resultData = convertData(apiResponseBody)
                    emit(Resource.success(resultData))
                } else {
                    emit(Resource.success(null))
                }
            } else {
                onFetchFailed()
                Timber.d("Error $apiResponse ${apiResponse.code()} ${apiResponse.message()} ${apiResponse.errorBody()}")
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
    }

    protected open fun onFetchFailed() {}

    protected open fun processResponse(response: Response<RequestType>) = response.body()

    protected abstract fun convertData(item: RequestType): ResultType

    protected abstract suspend fun createCall(): Response<RequestType>
}
