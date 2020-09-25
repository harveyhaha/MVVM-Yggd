package com.harveyhaha.sample.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import com.harveyhaha.sample.R
import com.harveyhaha.sample.databinding.ActivityLoginBinding
import com.harveyhaha.sample.ui.MainActivity
import com.harveyhaha.sample.utils.BrowserUtils
import com.harveyhaha.yggd.base.BaseActivity
import com.wtf.commonlib.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-8 下午2:15
 */
@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        Timber.i(viewModel.toString())
        viewModel.loginUser.observe(this, Observer {
            if (it != null) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user", it)
                startActivity(intent)
                finish()
            }
        })
    }

    override fun setBindingVariable() {
        super.setBindingVariable()
        binding.viewmodel = viewModel
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        viewModel.handleOauth(intent)
    }

    fun onLoginClick(view: View) {
        if (loginCheck()) {
            viewModel.basicLogin()
        }
    }

    fun onBrowserLoginClick(view: View) {
        val oauthTokenUrl = viewModel.getOAuth2Url()
        BrowserUtils.instance.openInBrowser(this, oauthTokenUrl)
    }

    private fun loginCheck(): Boolean {
        var checkValid = true
        if (TextUtils.isEmpty(viewModel.userName.get()?.trim())) {
            checkValid = false
            binding.usernameTextil.error = getString(R.string.user_name_warning)
        } else
            binding.usernameTextil.error = null
        if (TextUtils.isEmpty(viewModel.token.get()?.trim())) {
            checkValid = false
            binding.passwordTextil.error = getString(R.string.password_warning)
        } else
            binding.passwordTextil.error = null
        return checkValid
    }
}