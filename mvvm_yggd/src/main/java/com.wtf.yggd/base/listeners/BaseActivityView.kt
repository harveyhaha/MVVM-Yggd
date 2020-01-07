package com.wtf.yggd.base.listeners

import android.os.Bundle

/**
 * Created by wtf on 1/7/20 8:36 PM.
 */
interface BaseActivityView : BaseView {
    fun initContentView(savedInstanceState: Bundle?): Int
}