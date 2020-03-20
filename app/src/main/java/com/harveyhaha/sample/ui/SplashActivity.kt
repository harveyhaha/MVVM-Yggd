package com.harveyhaha.sample.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.harveyhaha.sample.R
import com.harveyhaha.sample.databinding.ActivitySplashBinding
import com.harveyhaha.sample.ui.login.LoginActivity
import com.harveyhaha.sample.viewmodels.SplashModel
import com.harveyhaha.yggd.base.BaseActivity

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

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        viewModel.loginUser.observe(this, Observer {
            if (it != null) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user", viewModel.loginUser.value)
                startActivity(intent)
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        })
        Handler().postDelayed({
            viewModel.checkLogin()
        }, 1000)
    }
}