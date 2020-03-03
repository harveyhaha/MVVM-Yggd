package com.harveyhaha.yggd.base.listeners

import android.os.Bundle

/**
 * Created by wtf on 1/7/20 8:36 PM.
 */
interface IBaseActivityView : IBaseView {
    fun initContentView(savedInstanceState: Bundle?): Int
}