package com.wtf.sample.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.wtf.sample.db.entity.AuthTokenEntity
import com.wtf.sample.db.entity.UserEntity
import com.wtf.sample.http.Resource
import com.wtf.sample.http.Status
import com.wtf.sample.repository.AccountRepository
import com.wtf.yggd.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-7 下午4:18
 */
open class LoginViewModel @Inject constructor(
    var accountRepository: AccountRepository,
    var gson: Gson
) :
    BaseViewModel() {
    var hasLogin: MutableLiveData<Boolean> = MutableLiveData()
    val userName = ObservableField<String>("harveyhaha")
    val token = ObservableField<String>("7aa83b9794008988b825b33834b307a215231f27")
    lateinit var authTokenEntity: AuthTokenEntity

    private var loginLiveData: LiveData<Resource<UserEntity>>? = null
    private var loginObserver = Observer<Resource<UserEntity>> {
        when (it.status) {
            Status.SUCCESS -> {
                hasLogin.postValue(true)
                Timber.i("登录成功 %s", gson.toJson(it.data))
            }
            Status.ERROR -> {
                hasLogin.postValue(false)
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

    override fun onCleared() {
        super.onCleared()
        loginLiveData?.removeObserver(loginObserver)
    }
}