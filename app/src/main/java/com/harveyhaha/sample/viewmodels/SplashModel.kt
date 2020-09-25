package com.harveyhaha.sample.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.harveyhaha.sample.db.entity.AuthTokenEntity
import com.harveyhaha.sample.db.entity.UserEntity
import com.harveyhaha.sample.repository.AccountRepository
import com.harveyhaha.yggd.base.BaseViewModel
import com.harveyhaha.yggd.http.Resource
import com.harveyhaha.yggd.http.Status
import com.harveyhaha.yggd.utils.observeOnce

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-14 上午11:35
 */
class SplashModel @ViewModelInject constructor(private var accountRepository: AccountRepository) :
    BaseViewModel() {
    var loginUser: MutableLiveData<UserEntity> = MutableLiveData()
    private lateinit var authTokenEntity: AuthTokenEntity
    private lateinit var loginObserver: Observer<Resource<UserEntity>>

    fun checkLogin() {
        accountRepository.getLoginToken().observeOnce {
            if (it == null) {
                loginUser.postValue(null)
            } else {
                authTokenEntity = it
                login(it)
            }
        }
    }

    private fun login(authTokenEntity: AuthTokenEntity) {
        val loginLiveData = accountRepository.login(authTokenEntity.token)
        loginObserver = Observer {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    loginUser.postValue(it.data)
                    loginLiveData.removeObserver(loginObserver)
                }
                Status.ERROR -> {
                    loginUser.postValue(it.data)
                    loginLiveData.removeObserver(loginObserver)
                }
            }
        }
        loginLiveData.observeForever(loginObserver)
    }
}