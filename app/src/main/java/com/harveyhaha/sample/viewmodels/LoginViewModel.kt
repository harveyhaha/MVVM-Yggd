package com.harveyhaha.sample.viewmodels

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.harveyhaha.sample.BuildConfig
import com.harveyhaha.sample.R
import com.harveyhaha.sample.config.OAUTH2_SCOPE
import com.harveyhaha.sample.config.OAUTH2_URL
import com.harveyhaha.sample.db.entity.AuthTokenEntity
import com.harveyhaha.sample.db.entity.UserEntity
import com.harveyhaha.sample.model.OauthToken
import com.harveyhaha.sample.repository.AccountRepository
import com.harveyhaha.yggd.base.BaseViewModel
import com.harveyhaha.yggd.http.Resource
import com.harveyhaha.yggd.http.Status
import timber.log.Timber
import java.util.*
import javax.inject.Inject

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-7 下午4:18
 */
open class LoginViewModel @Inject constructor(
    private var context: Context,
    private var accountRepository: AccountRepository,
    private var gson: Gson
) :
    BaseViewModel() {
    var loginUser: MutableLiveData<UserEntity> = MutableLiveData()
    val userName = ObservableField<String>("harveyhaha")
    val token = ObservableField<String>("7aa83b9794008988b825b33834b307a215231f27")
    lateinit var authTokenEntity: AuthTokenEntity

    private var loginLiveData: LiveData<Resource<UserEntity>>? = null
    private lateinit var loginObserver: Observer<Resource<UserEntity>>

    private lateinit var oauth2TokenObserver: Observer<Resource<OauthToken>>

    fun basicLogin() {
        cancelLogin()
        Timber.i("login:%s %s ", userName.get(), token.get())
        authTokenEntity = AuthTokenEntity(userName.get().toString(), token.get().toString(), false)
        loginLiveData = accountRepository.loginWithPersonalAccess(authTokenEntity)
        loginObserver = Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    loginUser.postValue(it.data)
                    Timber.i("登录成功 %s", gson.toJson(it.data))
                    loginLiveData?.removeObserver(loginObserver)
                }
                Status.ERROR -> {
                    loginUser.postValue(null)
                    loginLiveData?.removeObserver(loginObserver)
                }
                else -> {
                }
            }
        }
        loginLiveData?.observeForever(loginObserver)
    }

    private fun oauth2TokenLogin(token: String) {
        cancelLogin()
        loginLiveData = accountRepository.loginWithOauth2Token(token)
        loginObserver = Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    loginUser.postValue(it.data)
                    Timber.i("登录成功 %s", gson.toJson(it.data))
                    loginLiveData?.removeObserver(loginObserver)
                }
                Status.ERROR -> {
                    loginUser.postValue(null)
                    loginLiveData?.removeObserver(loginObserver)
                }
                else -> {
                }
            }
        }
        loginLiveData?.observeForever(loginObserver)
    }

    fun handleOauth(intent: Intent) {
        val uri = intent.data
        uri?.let {
            val code = uri.getQueryParameter("code")
            val state = uri.getQueryParameter("state")
            Timber.i("code %s state %s", code, state)
            getOauth2Token(code, state)
        }
    }

    private fun getOauth2Token(code: String?, state: String?) {
        val oauth2TokenLiveData = accountRepository.getOauth2Token(
            BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET,
            code,
            state
        )
        oauth2TokenObserver = Observer { it ->
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let {
                        val token = it.access_token
                        val scope = it.scope
                        val type = it.token_type
                        oauth2TokenLogin(token)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(context, R.string.login_error_cannot_get_oauth2_token, Toast.LENGTH_SHORT).show()
                }
                else -> {
                }
            }
        }
        oauth2TokenLiveData.observeForever(oauth2TokenObserver)
    }

    fun getOAuth2Url(): String {
        val randomState = UUID.randomUUID().toString()
        return OAUTH2_URL +
                "?client_id=" + BuildConfig.CLIENT_ID +
                "&scope=" + OAUTH2_SCOPE +
                "&state=" + randomState
    }

    private fun cancelLogin() {
        loginLiveData?.removeObserver(loginObserver)
    }

    override fun onCleared() {
        super.onCleared()
        cancelLogin()
    }
}