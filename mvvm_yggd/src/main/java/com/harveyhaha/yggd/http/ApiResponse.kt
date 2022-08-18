package com.harveyhaha.yggd.http

import com.harveyhaha.yggd.http.gsonconvert.ServiceErrorException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * @Description:    ApiResponse
 * @Author:         harveyhaha
 * @CreateDate:     20-1-10 下午5:34
 */
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            error.printStackTrace()
            var errorCode = -1
            var errorMessage = "unknown error"
            when (error) {
                is ServiceErrorException -> {
                    errorCode = error.errorCode
                    error.message?.let { errorMessage = it }
                }
                is SocketTimeoutException -> {
                    errorCode = 503
                    errorMessage = "连接超时"
                }
                is ConnectException->{
                    errorCode = 504
                    errorMessage = "网络连接异常"
                }
                else -> {
                    error.message?.let { errorMessage = it }
                }
            }
            return ApiErrorResponse(errorCode, errorMessage)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(-1, errorMsg ?: "unknown error")
            }
        }
    }
}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

class ApiSuccessResponse<T>(var body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(var errorCode: Int, val errorMessage: String) : ApiResponse<T>()
