package com.wtf.commonlib.http

import okhttp3.Interceptor
import okhttp3.Request

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-20 上午10:59
 */
interface GlobalHttpHandler {
    fun httpRequestBefore(chain: Interceptor.Chain, request: Request): Request
}