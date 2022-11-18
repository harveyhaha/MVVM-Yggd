package com.harveyhaha.yggd.base.listeners

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * Created by wtf on 1/7/20 8:36 PM.
 */
interface IBaseFragmentView : IBaseView {
    /**
     * 初始化根布局
     **/
    fun initContentView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): Int
    fun getAppCompatActivity(): AppCompatActivity
    fun initToolbar(toolbar: Toolbar, popBackCallBack: () -> Unit)
}