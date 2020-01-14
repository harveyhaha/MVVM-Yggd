package com.wtf.sample.api

import androidx.lifecycle.LiveData
import com.wtf.sample.db.entity.UserEntity
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
    //login
//    @POST("authorizations")
//    @Headers("Accept: application/json")
//    fun authorizations(
//        @NonNull @Body authRequestModel: AuthRequestModel?
//    ): Observable<Response<BasicToken?>?>?

//    @POST("login/oauth/access_token")
//    @Headers("Accept: application/json")
//    fun getAccessToken(
//        @Query("client_id") clientId: String?,
//        @Query("client_secret") clientSecret: String?,
//        @Query("code") code: String?,
//        @Query("state") state: String?
//    ): Observable<Response<OauthToken?>?>?
}