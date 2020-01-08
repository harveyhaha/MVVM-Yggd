package com.wtf.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wtf.sample.R
import com.wtf.sample.databinding.FragmentMainBinding
import com.wtf.sample.viewmodels.MainViewModel
import com.wtf.yggd.base.BaseFragment
import timber.log.Timber

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-8 下午2:15
 */
class LoginFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {
    override fun initContentView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int {
        return R.layout.fragment_main
    }

    override fun initViewModel(): MainViewModel? {
        return activity?.let {
            ViewModelProviders.of(it, viewModelFactory).get(MainViewModel::class.java)
        }
    }

    override fun initView() {
        super.initView()
        Timber.i(viewModel.toString())
    }
}