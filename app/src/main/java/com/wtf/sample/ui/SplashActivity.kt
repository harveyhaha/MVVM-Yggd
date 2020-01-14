package com.wtf.sample.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.wtf.sample.R
import com.wtf.sample.databinding.ActivitySplashBinding
import com.wtf.sample.ui.login.LoginActivity
import com.wtf.sample.viewmodels.SplashModel
import com.wtf.yggd.base.BaseActivity

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-8 下午2:59
 */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        super.initView()
        viewModel?.hasLogin?.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        })
        Handler().postDelayed({
            viewModel?.checkLogin()
        }, 2000)
    }
}