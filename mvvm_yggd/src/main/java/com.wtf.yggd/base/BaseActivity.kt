package com.wtf.yggd.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import dagger.android.AndroidInjection

/**
 * Created by wtf on 1/5/20 10:10 PM.
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    abstract val layoutId: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }

//    fun initViewDataBinding(savedInstanceState: Bundle?) {
//        newsListViewModel = viewModelFactory.create(NewsListViewModel::class.java)
//
//    }
}