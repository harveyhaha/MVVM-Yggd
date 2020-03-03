package com.harveyhaha.sample.api

import androidx.lifecycle.LiveData
import com.harveyhaha.sample.model.Event
import com.harveyhaha.sample.model.User
import com.harveyhaha.yggd.http.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-10 下午4:39
 */
interface HttpServiceApi {
    @GET("user")
    fun login(@Header("Authorization") token: String): LiveData<ApiResponse<User>>

    @GET("users/{username}/received_events")
    fun getPrivateReceivedEvents(@Path("username") username: String, @Query("page") page: Int): LiveData<ApiResponse<MutableList<Event>>>
}