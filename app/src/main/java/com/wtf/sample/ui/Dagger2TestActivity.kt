package com.wtf.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.wtf.sample.R
import com.wtf.sample.api.HttpServiceApi
import com.wtf.sample.databinding.ActivityTestBinding
import dagger.android.AndroidInjection
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
class Dagger2TestActivity : AppCompatActivity() {
    lateinit var binding: ActivityTestBinding
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)
        Timber.i(gson.toString())
        Timber.i(coroutineContext.toString())
        Timber.i(passwordWarning)
        Timber.i(okHttpClient.toString())
        Timber.i(httpServiceApi.toString())
    }


}