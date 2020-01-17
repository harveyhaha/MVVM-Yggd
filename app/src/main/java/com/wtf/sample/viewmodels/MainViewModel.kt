package com.wtf.sample.viewmodels

import androidx.lifecycle.MutableLiveData
import com.wtf.sample.db.entity.UserEntity
import com.wtf.sample.repository.AccountRepository
import com.wtf.yggd.base.BaseViewModel
import javax.inject.Inject

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-7 下午4:18
 */
class MainViewModel @Inject constructor(var accountRepository: AccountRepository) :
    BaseViewModel() {
    var loginedUser: MutableLiveData<UserEntity> = MutableLiveData()

    fun logout() {
        accountRepository.logout(loginedUser.value)
        loginedUser.postValue(null)
    }
}