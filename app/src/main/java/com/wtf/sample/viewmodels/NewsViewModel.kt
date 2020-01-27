package com.wtf.sample.viewmodels

import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.loadmore.LoadMoreStatus
import com.wtf.sample.R
import com.wtf.sample.app.MyApplication
import com.wtf.sample.model.Event
import com.wtf.sample.model.RecycleLoadMoreStatus
import com.wtf.sample.repository.AccountRepository
import com.wtf.yggd.base.BaseViewModel
import com.wtf.yggd.http.Resource
import com.wtf.yggd.http.Status
import javax.inject.Inject

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-21 上午9:47
 */
class NewsViewModel @Inject constructor(var accountRepository: AccountRepository) :
    BaseViewModel() {
    lateinit var username: String
    var eventDataList: MutableLiveData<MutableList<Event>> = MutableLiveData()
    private var _eventDataList = mutableListOf<Event>()
    lateinit var privateReceiveEventsObserver: Observer<Resource<MutableList<Event>>>
    private var page = 1
    //gone if true gone the load more view
    var recycleLoadMoreStatus: MutableLiveData<RecycleLoadMoreStatus> = MutableLiveData()
    var swipeRefreshLayoutIsEnable = ObservableBoolean(true)
    var swipeRefreshLayoutIsRefreshing = ObservableBoolean(true)

    companion object {
        const val TOTAL_COUNTER = 300
        const val PAGE_SIZE = 30
    }

    fun getPrivateReceiveEventsLoadMore() {
        getPrivateReceiveEvents(page)
    }

    fun getPrivateReceiveEvents(page: Int = 1) {
        val isRefreshing = page == 1
        if (isRefreshing) {
            swipeRefreshLayoutIsRefreshing.set(true)
            recycleLoadMoreStatus.postValue(RecycleLoadMoreStatus(null, false))
        } else {
            swipeRefreshLayoutIsEnable.set(false)
            recycleLoadMoreStatus.postValue(RecycleLoadMoreStatus(null, true))
        }
        val privateReceiveEventsLiveData = accountRepository.getUserPrivateReceiveEvents(username, page)
        privateReceiveEventsObserver = Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { data ->
                        this@NewsViewModel.page += 1
                        if (isRefreshing) {
                            _eventDataList.clear()
                            _eventDataList.addAll(data)
                            swipeRefreshLayoutIsRefreshing.set(false)
                            recycleLoadMoreStatus.postValue(RecycleLoadMoreStatus(null, true))
                        } else {
                            _eventDataList.addAll(data)
                            when {
                                _eventDataList.size >= TOTAL_COUNTER -> {
                                    //complete
                                    recycleLoadMoreStatus.postValue(
                                        RecycleLoadMoreStatus(
                                            LoadMoreStatus.Complete
                                        )
                                    )
                                }
                                data.size < PAGE_SIZE -> {
                                    recycleLoadMoreStatus.postValue(
                                        RecycleLoadMoreStatus(
                                            LoadMoreStatus.End,
                                            isRefreshing
                                        )
                                    )
                                }
                                else -> {
                                    //complete
                                    recycleLoadMoreStatus.postValue(
                                        RecycleLoadMoreStatus(
                                            LoadMoreStatus.Complete
                                        )
                                    )
                                }
                            }
                            swipeRefreshLayoutIsEnable.set(true)
                        }
                        eventDataList.postValue(_eventDataList)
                    }
                    privateReceiveEventsLiveData.removeObserver(privateReceiveEventsObserver)
                }
                Status.ERROR -> {
                    Toast.makeText(
                        MyApplication.context,
                        R.string.login_error_cannot_get_oauth2_token,
                        Toast.LENGTH_SHORT
                    ).show()
                    if (isRefreshing) {
                        swipeRefreshLayoutIsRefreshing.set(false)
                    } else {
                        recycleLoadMoreStatus.postValue(RecycleLoadMoreStatus(LoadMoreStatus.Fail))
                    }
                    eventDataList.postValue(null)
                    privateReceiveEventsLiveData.removeObserver(privateReceiveEventsObserver)
                }
                Status.LOADING -> {
                    recycleLoadMoreStatus.postValue(RecycleLoadMoreStatus(LoadMoreStatus.Loading))
                }
            }
        }
        privateReceiveEventsLiveData.observeForever(privateReceiveEventsObserver)
    }

}