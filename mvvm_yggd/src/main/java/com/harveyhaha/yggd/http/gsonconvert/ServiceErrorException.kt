package com.harveyhaha.yggd.http.gsonconvert

import java.io.IOException

/**
 *
 * @Description:
 * @Author:         wutengfei
 * @CreateDate:     2021/1/16 10:40
 */
class ServiceErrorException : IOException {
    var errorCode = 0

    constructor(errorCode: Int, errorMsg: String?) : super(errorMsg) {
        this.errorCode = errorCode
    }

    constructor(e: Exception?) : super(e)
}