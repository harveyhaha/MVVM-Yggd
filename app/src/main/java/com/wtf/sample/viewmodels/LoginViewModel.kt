package com.wtf.sample.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.wtf.sample.BuildConfig
import com.wtf.sample.config.OAUTH2_SCOPE
import com.wtf.sample.config.OAUTH2_URL
import com.wtf.sample.db.entity.AuthTokenEntity
import com.wtf.sample.db.entity.UserEntity
import com.wtf.sample.repository.AccountRepository
import com.wtf.yggd.base.BaseViewModel
import com.wtf.yggd.http.Resource
import com.wtf.yggd.http.Status
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
    private var accountRepository: AccountRepository,
    private var gson: Gson
) :
    BaseViewModel() {
    var loginUser: MutableLiveData<UserEntity> = MutableLiveData()
    val userName = ObservableField<String>("harveyhaha")
    val token = ObservableField<String>("7aa83b9794008988b825b33834b307a215231f27")
    lateinit var authTokenEntity: AuthTokenEntity

    private var loginLiveData: LiveData<Resource<UserEntity>>? = null
    private var loginObserver = Observer<Resource<UserEntity>> {
        when (it.status) {
            Status.SUCCESS -> {
                loginUser.postValue(it.data)
                Timber.i("登录成功 %s", gson.toJson(it.data))
            }
            Status.ERROR -> {
                loginUser.postValue(null)
            }
            else -> {
            }
        }
    }

    fun basicLogin() {
        Timber.i("login:%s %s ", userName.get(), token.get())
        authTokenEntity =
            AuthTokenEntity(userName.get().toString(), token.get().toString(), false)
        loginLiveData?.removeObserver(loginObserver)
        loginLiveData = accountRepository.login(authTokenEntity)
        loginLiveData?.observeForever(loginObserver)
    }

    open fun getOAuth2Url(): String {
        val randomState = UUID.randomUUID().toString()
        return OAUTH2_URL +
                "?client_id=" + BuildConfig.CLIENT_ID +
                "&scope=" + OAUTH2_SCOPE +
                "&state=" + randomState
    }

    override fun onCleared() {
        super.onCleared()
        loginLiveData?.removeObserver(loginObserver)
    }
}