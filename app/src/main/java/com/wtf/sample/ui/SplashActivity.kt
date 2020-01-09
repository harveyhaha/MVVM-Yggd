package com.wtf.sample.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.wtf.sample.R
import com.wtf.sample.databinding.ActivitySplashBinding
import com.wtf.yggd.base.BaseActivity
import com.wtf.yggd.base.BaseViewModel

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-8 下午2:59
 */
class SplashActivity : BaseActivity<ActivitySplashBinding, BaseViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        super.initView()
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}