package com.wtf.yggd.base.listeners

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable

/**
 * Created by wtf on 1/7/20 8:36 PM.
 */
interface IBaseFragmentView : IBaseView {
    /**
     * 初始化根布局
     **/
    fun initContentView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): Int

}