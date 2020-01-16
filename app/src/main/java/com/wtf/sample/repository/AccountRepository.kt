package com.wtf.sample.repository

import androidx.lifecycle.LiveData
import com.wtf.sample.api.HttpServiceApi
import com.wtf.sample.db.AuthTokenDao
import com.wtf.sample.db.UserDao
import com.wtf.sample.db.entity.AuthTokenEntity
import com.wtf.sample.db.entity.UserEntity
import com.wtf.yggd.http.ApiResponse
import com.wtf.yggd.http.NetworkBoundResource
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
    private val httpServiceApi: HttpServiceApi
) {
    fun getLoginToken(): LiveData<AuthTokenEntity> {
        return authTokenDao.getLoginToken()
    }

    fun getLoginUser(): LiveData<UserEntity> {
        return userDao.getLoginUser()
    }

    /**
     *
     */
    fun login(authTokenEntity: AuthTokenEntity): LiveData<Resource<UserEntity>> {
        val basicToken = Credentials.basic(authTokenEntity.login, authTokenEntity.token)
        return object : NetworkBoundResource<UserEntity, UserEntity>(appExecutors) {
            override fun saveCallResult(item: UserEntity) {
                userDao.insertUser(item)
                authTokenEntity.isLogin = true
                authTokenDao.insertToken(authTokenEntity)
            }

            override fun shouldFetch(data: UserEntity?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<UserEntity> {
                return userDao.getLoginUser(authTokenEntity.login, authTokenEntity.token)
            }

            override fun createCall(): LiveData<ApiResponse<UserEntity>> {
                return httpServiceApi.login(basicToken)
            }

        }.asLiveData()
    }
}