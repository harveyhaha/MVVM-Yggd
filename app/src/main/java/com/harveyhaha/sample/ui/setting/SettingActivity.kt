package com.harveyhaha.sample.ui.setting

import android.os.Bundle
import com.harveyhaha.sample.R
import com.harveyhaha.sample.busevent.RecreateEvent
import com.harveyhaha.sample.databinding.ActivitySettingBinding
import com.harveyhaha.yggd.base.BaseActivity
import com.harveyhaha.yggd.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-2-2 下午2:15
 */
@AndroidEntryPoint
class SettingActivity : BaseActivity<ActivitySettingBinding, BaseViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_setting
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let { actionBar ->
            actionBar.title = getString(R.string.setting)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: RecreateEvent) {
        recreate()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}