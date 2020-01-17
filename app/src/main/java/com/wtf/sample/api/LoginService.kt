package com.wtf.sample.api

import androidx.lifecycle.LiveData
import com.wtf.sample.model.OauthToken
import com.wtf.yggd.http.ApiResponse
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-17 下午5:50
 */
interface LoginService {
    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    fun getOauth2Token(
        @Query("client_id") client_id: String,
        @Query("client_secret") client_secret: String,
        @Query("code") code: String?,
        @Query("state") state: String?
    ): LiveData<ApiResponse<OauthToken>>
}