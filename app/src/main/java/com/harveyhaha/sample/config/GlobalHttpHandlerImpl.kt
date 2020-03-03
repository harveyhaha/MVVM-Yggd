package com.harveyhaha.sample.config

import com.harveyhaha.sample.http.GlobalHttpHandler
import okhttp3.Interceptor
import okhttp3.Request
import timber.log.Timber

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-20 上午11:12
 */
class GlobalHttpHandlerImpl : GlobalHttpHandler {
    var basicToken: String? = null
    override fun httpRequestBefore(chain: Interceptor.Chain, request: Request): Request {
        val requestBuilder = request.newBuilder()
        basicToken?.let {
            addToken(requestBuilder, it)
        }
        val newRequest = requestBuilder.build()
        Timber.i("request %s", newRequest.toString())
        return newRequest
    }

    private fun addToken(requestBuilder: Request.Builder, token: String): Request.Builder {
        Timber.i("save token %s", token)
        return requestBuilder.addHeader("Authorization", token)
    }
}