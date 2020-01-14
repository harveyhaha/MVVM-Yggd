package com.wtf.sample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.wtf.sample.db.entity.AuthTokenEntity
import com.wtf.sample.db.entity.UserEntity
import com.wtf.sample.http.Resource
import com.wtf.sample.http.Status
import com.wtf.sample.repository.AccountRepository
import com.wtf.yggd.base.BaseViewModel
import com.wtf.yggd.utils.observeOnce
import javax.inject.Inject

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-14 上午11:35
 */
class SplashModel @Inject constructor(var accountRepository: AccountRepository) :
    BaseViewModel() {
    var hasLogin: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var authTokenEntity: AuthTokenEntity
    private var loginLiveData: LiveData<Resource<UserEntity>>? = null
    private var loginObserver = Observer<Resource<UserEntity>> {
        when (it.status) {
            Status.LOADING -> {
            }
            Status.SUCCESS -> {
                hasLogin.postValue(true)
            }
            Status.ERROR -> {
                hasLogin.postValue(false)
            }
        }
    }

    fun checkLogin() {
        accountRepository.getLoginToken().observeOnce {
            if (it == null) {
                hasLogin.postValue(false)
            } else {
                authTokenEntity = it
                login(it)
            }
        }
    }

    private fun login(authTokenEntity: AuthTokenEntity) {
        loginLiveData?.removeObserver(loginObserver)
        loginLiveData = accountRepository.login(authTokenEntity)
        loginLiveData?.observeForever(loginObserver)
    }
}

