package com.wtf.sample.repository

import androidx.lifecycle.LiveData
import com.wtf.sample.api.HttpServiceApi
import com.wtf.sample.api.LoginService
import com.wtf.sample.db.AuthTokenDao
import com.wtf.sample.db.UserDao
import com.wtf.sample.db.entity.AuthTokenEntity
import com.wtf.sample.db.entity.UserEntity
import com.wtf.sample.model.OauthToken
import com.wtf.yggd.http.ApiResponse
import com.wtf.yggd.http.NetworkBoundResource
import com.wtf.yggd.http.NetworkOnlyFetchResource
import com.wtf.yggd.http.Resource
import com.wtf.yggd.utils.AppExecutors
import okhttp3.Credentials
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-10 下午5:58
 */
@Singleton
class AccountRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val authTokenDao: AuthTokenDao,
    private val userDao: UserDao,
    private val httpServiceApi: HttpServiceApi,
    private val loginService: LoginService
) {
    fun getLoginToken(): LiveData<AuthTokenEntity> {
        return authTokenDao.getLoginToken()
    }

    fun getLoginUser(): LiveData<UserEntity> {
        return userDao.getLoginUser()
    }

    fun loginWithPersonalAccess(authTokenEntity: AuthTokenEntity): LiveData<Resource<UserEntity>> {
        val basicToken = Credentials.basic(authTokenEntity.login, authTokenEntity.token)
        return login(basicToken)
    }

    fun loginWithOauth2Token(token: String): LiveData<Resource<UserEntity>> {
        val basicToken = "token $token"
        return login(basicToken)
    }

    fun login(token: String): LiveData<Resource<UserEntity>> {
        return object : NetworkBoundResource<UserEntity, UserEntity>(appExecutors) {
            override fun saveCallResult(item: UserEntity) {
                userDao.insertUser(item)
                authTokenDao.insertToken(AuthTokenEntity(item.login, token, true))
            }

            override fun shouldFetch(data: UserEntity?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<UserEntity> {
                return userDao.getLoginUser(token)
            }

            override fun createCall(): LiveData<ApiResponse<UserEntity>> {
                return httpServiceApi.login(token)
            }

        }.asLiveData()
    }

    fun logout(userEntity: UserEntity?) {
        userEntity?.let {
            appExecutors.diskIO().execute {
                authTokenDao.deleteAuthToken(userEntity.login)
                userDao.deleteUser(userEntity)
            }
        }
    }

    fun getOauth2Token(
        client_id: String,
        client_secret: String,
        code: String?,
        state: String?
    ): LiveData<Resource<OauthToken>> {
        return object : NetworkOnlyFetchResource<OauthToken>(appExecutors) {
            override fun createCall(): LiveData<ApiResponse<OauthToken>> {
                return loginService.getOauth2Token(
                    client_id,
                    client_secret,
                    code,
                    state
                )
            }
        }.asLiveData()
    }
}