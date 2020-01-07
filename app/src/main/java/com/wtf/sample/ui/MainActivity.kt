package com.wtf.sample.ui

import android.os.Bundle
import com.wtf.sample.R
import com.wtf.sample.databinding.ActivityMainBinding
import com.wtf.sample.viewmodels.MainViewModel
import com.wtf.yggd.base.BaseActivity

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:53
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

}
