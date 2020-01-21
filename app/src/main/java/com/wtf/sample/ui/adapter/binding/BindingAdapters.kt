package com.wtf.sample.ui.adapter.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 *
 * @Description:    双向绑定
 * @Author:         harveyhaha
 * @CreateDate:     20-1-10 上午10:25
 */
object BindingAdapters {

    @BindingAdapter("app:isRefreshing")
    @JvmStatic
    fun swipeRefreshLayoutIsRefreshing(layout: SwipeRefreshLayout, isRefreshing: Boolean) {
        layout.isRefreshing = isRefreshing
    }

    @BindingAdapter("app:isEnable")
    @JvmStatic
    fun swipeRefreshLayoutIsEnable(layout: SwipeRefreshLayout, isEnable: Boolean) {
        layout.isEnabled = isEnable
    }
}