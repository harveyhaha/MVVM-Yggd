package com.wtf.commonlib.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.harveyhaha.sample.db.entity.UserEntity
import com.harveyhaha.yggd.base.BaseViewModel
import com.wtf.commonlib.repository.AccountRepository

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-7 下午4:18
 */
class MainViewModel @ViewModelInject constructor(var accountRepository: AccountRepository, var gson: Gson) :
    BaseViewModel() {
    var loginedUser: MutableLiveData<UserEntity> = MutableLiveData()

    fun logout() {
        accountRepository.logout(loginedUser.value)
        loginedUser.postValue(null)
    }
}