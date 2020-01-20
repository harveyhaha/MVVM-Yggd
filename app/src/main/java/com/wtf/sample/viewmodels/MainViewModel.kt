package com.wtf.sample.viewmodels

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.wtf.sample.R
import com.wtf.sample.app.MyApplication.Companion.context
import com.wtf.sample.db.entity.UserEntity
import com.wtf.sample.model.Event
import com.wtf.sample.repository.AccountRepository
import com.wtf.yggd.base.BaseViewModel
import com.wtf.yggd.http.Resource
import com.wtf.yggd.http.Status
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-7 下午4:18
 */
class MainViewModel @Inject constructor(var accountRepository: AccountRepository, var gson: Gson) :
    BaseViewModel() {
    var loginedUser: MutableLiveData<UserEntity> = MutableLiveData()
    lateinit var privateReceiveEventsObserver: Observer<Resource<Event>>

    fun logout() {
        accountRepository.logout(loginedUser.value)
        loginedUser.postValue(null)
    }

    fun getPrivateReceiveEvents() {
        loginedUser.value?.login?.let { it ->
            val privateReceiveEventsLiveData = accountRepository.getUserPrivateReceiveEvents(it)
            privateReceiveEventsObserver = Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { data ->
                        }
                        privateReceiveEventsLiveData.removeObserver(privateReceiveEventsObserver)
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, R.string.login_error_cannot_get_oauth2_token, Toast.LENGTH_SHORT).show()
                        privateReceiveEventsLiveData.removeObserver(privateReceiveEventsObserver)
                    }
                    else -> {
                    }
                }
            }
            privateReceiveEventsLiveData.observeForever(privateReceiveEventsObserver)
        }
    }
}