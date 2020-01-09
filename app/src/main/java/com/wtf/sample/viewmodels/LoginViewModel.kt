package com.wtf.sample.viewmodels

import com.wtf.yggd.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-7 下午4:18
 */
open class LoginViewModel @Inject constructor() : BaseViewModel() {
    var userName: String = ""
    var password: String = ""
    fun basicLogin() {
        Timber.i("login:", userName, password)
    }

    fun getToken(code: String, state: String) {

    }

//    fun getUserInfo(basicToken: BasicToken?) {
//
//    }

}