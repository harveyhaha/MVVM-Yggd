package com.wtf.sample.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.wtf.sample.R
import com.wtf.sample.databinding.ActivityLoginBinding
import com.wtf.sample.viewmodels.LoginViewModel
import com.wtf.yggd.base.BaseActivity
import timber.log.Timber

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-8 下午2:15
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        super.initView()
        Timber.i(viewModel.toString())
    }

    override fun setBindingVariable() {
        super.setBindingVariable()
        binding.viewmodel = viewModel
    }

    override fun initData() {
        super.initData()
    }

    fun onLoginClick(view: View) {
        if (loginCheck()) {
            viewModel?.basicLogin()
        }
    }

    private fun loginCheck(): Boolean {
        var checkValid = true
        if (TextUtils.isEmpty(viewModel?.userName?.get()?.trim())) {
            checkValid = false
            binding.usernameTextil.error = getString(R.string.user_name_warning)
        } else
            binding.usernameTextil.error = null
        if (TextUtils.isEmpty(viewModel?.password?.get()?.trim())) {
            checkValid = false
            binding.passwordTextil.error = getString(R.string.password_warning)
        } else
            binding.passwordTextil.error = null
        Timber.i(
            "username %s password %s",
            viewModel?.userName?.get(),
            viewModel?.password?.get()
        )
        return checkValid
    }
}