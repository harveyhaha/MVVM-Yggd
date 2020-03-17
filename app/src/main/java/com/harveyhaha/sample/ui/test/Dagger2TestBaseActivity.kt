package com.harveyhaha.sample.ui.test

import android.os.Bundle
import com.google.gson.Gson
import com.harveyhaha.sample.R
import com.harveyhaha.sample.api.HttpServiceApi
import com.harveyhaha.sample.databinding.ActivityTestBinding
import com.harveyhaha.yggd.base.BaseActivity
import com.harveyhaha.yggd.base.BaseViewModel
import okhttp3.OkHttpClient
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-15 下午5:28
 */
class Dagger2TestBaseActivity : BaseActivity<ActivityTestBinding, BaseViewModel>() {
    @Inject
    lateinit var gson: Gson
    @Inject
    lateinit var coroutineContext: CoroutineContext
    @Inject
    @Named("passwordWarning")
    lateinit var passwordWarning: String
    @Inject
    lateinit var okHttpClient: OkHttpClient
    @Inject
    lateinit var httpServiceApi: HttpServiceApi

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_test
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        Timber.i(gson.toString())
        Timber.i(coroutineContext.toString())
        Timber.i(passwordWarning)
        Timber.i(okHttpClient.toString())
        Timber.i(httpServiceApi.toString())
    }

}