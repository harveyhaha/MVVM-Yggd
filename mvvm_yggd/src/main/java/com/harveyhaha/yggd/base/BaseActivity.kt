package com.harveyhaha.yggd.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.harveyhaha.yggd.base.listeners.IBaseActivityView
import com.harveyhaha.yggd.di.ViewModelFactory
import com.harveyhaha.yggd.utils.AppManager
import dagger.android.AndroidInjection
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject


/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:58
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    IBaseActivityView {
    open lateinit var binding: V
    open var viewModel: VM? = null
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
        initViewDataBinding(savedInstanceState)
        //页面接受的参数方法
        initParam()
        initView(savedInstanceState)
        initData()
    }

    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState))
        binding.lifecycleOwner = this
        viewModel = initViewModel()
        if (viewModel == null) {
            val type: ParameterizedType = javaClass.genericSuperclass as ParameterizedType
            val actualTypeArguments: Array<Type> = type.actualTypeArguments
            val modelClass: Class<VM> = actualTypeArguments[1] as Class<VM>
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(modelClass)
        }
        setBindingVariable()
    }

    abstract override fun initContentView(savedInstanceState: Bundle?): Int

    open fun initViewModel(): VM? {
        return null
    }

    override fun initParam() {}

    override fun initView(savedInstanceState: Bundle?) {}

    override fun initData() {}

    /**
     * binding 绑定 ViewModel 操作
     */
    override fun setBindingVariable() {}

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        GlideApp.with(this).onTrimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        GlideApp.with(this).onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
        AppManager.instance.removeActivity(this)
    }
}