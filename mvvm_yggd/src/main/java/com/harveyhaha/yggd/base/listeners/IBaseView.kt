package com.harveyhaha.yggd.base.listeners

import android.os.Bundle
import androidx.lifecycle.ViewModelStoreOwner

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-7 上午11:19
 */
interface IBaseView {
    fun getViewModelStoreOwner(): ViewModelStoreOwner
    fun initParam()
    fun setBindingVariable()
    fun initView(savedInstanceState: Bundle?)
    fun initData()
}