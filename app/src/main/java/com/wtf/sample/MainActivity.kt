package com.wtf.sample

import android.os.Bundle
import com.google.gson.Gson
import com.wtf.sample.databinding.ActivityMainBinding
import com.wtf.sample.ui.ViewModelFactory
import com.wtf.sample.viewmodels.MainViewModel
import com.wtf.yggd.base.BaseActivity
import javax.inject.Inject

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:53
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var gson: Gson
//    @Inject
//    lateinit var context: CoroutineContext

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

}
