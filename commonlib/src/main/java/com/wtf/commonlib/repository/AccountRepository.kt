package com.wtf.commonlib.repository

import androidx.lifecycle.LiveData
import com.harveyhaha.sample.db.entity.UserEntity
import com.harveyhaha.yggd.http.ApiResponse
import com.harveyhaha.yggd.http.NetworkBoundResource
import com.harveyhaha.yggd.http.NetworkResource
import com.harveyhaha.yggd.http.Resource
import com.harveyhaha.yggd.utils.AppExecutors
import com.wtf.commonlib.api.HttpServiceApi
import com.wtf.commonlib.api.LoginService
import com.wtf.commonlib.config.GlobalHttpHandlerImpl
import com.wtf.commonlib.db.AuthTokenDao
import com.wtf.commonlib.db.UserDao
import com.wtf.commonlib.entity.AuthTokenEntity
import com.wtf.commonlib.model.Event
import com.wtf.commonlib.model.OauthToken
import com.wtf.commonlib.model.User
import okhttp3.Credentials
import javax.inject.Inject

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-10 下午5:58
 */
class AccountRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val authTokenDao: AuthTokenDao,
    private val userDao: UserDao,
    private val httpServiceApi: HttpServiceApi,
    private val loginService: LoginService,
    private val globalHttpHandler: GlobalHttpHandlerImpl?,
) {
    fun getLoginToken(): LiveData<AuthTokenEntity> {
        return authTokenDao.getLoginToken()
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
        return object : NetworkBoundResource<UserEntity, User>(appExecutors) {
            override fun saveCallResult(item: User) {
                globalHttpHandler?.let {
                    it.basicToken = token
                }
                userDao.insertUser(item.toUserEntity())
                authTokenDao.insertToken(AuthTokenEntity(item.login, token, true))
            }

            override fun shouldFetch(data: UserEntity?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<UserEntity> {
                return userDao.getLoginUser(token)
            }

            override fun createCall(): LiveData<ApiResponse<User>> {
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
        state: String?,
    ): LiveData<Resource<OauthToken>> {
        return object : NetworkResource<OauthToken,OauthToken>(appExecutors) {
            override fun createCall(): LiveData<ApiResponse<OauthToken>> {
                return loginService.getOauth2Token(
                    client_id,
                    client_secret,
                    code,
                    state
                )
            }

            override fun convertData(item: OauthToken): OauthToken {
                return item
            }
        }.asLiveData()
    }

    fun getUserPrivateReceiveEvents(username: String, page: Int): LiveData<Resource<MutableList<Event>>> {
        return object : NetworkResource<MutableList<Event>, MutableList<Event>>(appExecutors) {
            override fun createCall(): LiveData<ApiResponse<MutableList<Event>>> {
                return httpServiceApi.getPrivateReceivedEvents(username, page)
            }

            override fun convertData(item: MutableList<Event>): MutableList<Event> {
                return item
            }
        }.asLiveData()
    }
}