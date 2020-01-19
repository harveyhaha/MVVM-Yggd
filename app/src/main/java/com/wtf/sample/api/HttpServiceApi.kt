package com.wtf.sample.api

import androidx.lifecycle.LiveData
import com.wtf.sample.db.entity.UserEntity
import com.wtf.yggd.http.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-10 下午4:39
 */
interface HttpServiceApi {
    @GET("user")
    fun login(@Header("Authorization") token: String): LiveData<ApiResponse<UserEntity>>
}