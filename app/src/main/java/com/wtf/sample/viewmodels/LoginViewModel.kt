package com.wtf.sample.viewmodels

import androidx.databinding.ObservableField
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
    val userName = ObservableField<String>("")
    val password = ObservableField<String>("")

    fun basicLogin() {
        Timber.i("login:%s %s ", userName.get(), password.get())
    }

    fun getToken(code: String, state: String) {

    }

//    fun getUserInfo(basicToken: BasicToken?) {
//
//    }

}