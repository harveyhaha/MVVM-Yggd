package com.wtf.sample.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.wtf.sample.db.entity.AuthTokenEntity
import com.wtf.sample.db.entity.UserEntity
import com.wtf.sample.repository.AccountRepository
import com.wtf.yggd.base.BaseViewModel
import com.wtf.yggd.http.Resource
import com.wtf.yggd.http.Status
import com.wtf.yggd.utils.observeOnce
import javax.inject.Inject

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-14 上午11:35
 */
class SplashModel @Inject constructor(private var accountRepository: AccountRepository) :
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