package com.harveyhaha.yggd.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.harveyhaha.yggd.base.listeners.IBaseActivityView
import com.harveyhaha.yggd.utils.AppManager
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:58
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    IBaseActivityView {
    private var _binding: V? = null
    val binding get() = _binding!!
    open lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
        initViewDataBinding(savedInstanceState)
        // 页面接受的参数方法
        initParam()
        initView(savedInstanceState)
        initData()

    }

    @Suppress("UNCHECKED_CAST")
    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        _binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState))
        _binding?.lifecycleOwner = this
        val type: ParameterizedType = javaClass.genericSuperclass as ParameterizedType
        val actualTypeArguments: Array<Type> = type.actualTypeArguments
        val modelClass: Class<VM> = actualTypeArguments[1] as Class<VM>
        viewModel =
            ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(modelClass)
        setBindingVariable()
    }

    abstract override fun initContentView(savedInstanceState: Bundle?): Int

    override fun isActivityViewModel(): Boolean {
        return true
    }

    override fun initParam() {}

    override fun initView(savedInstanceState: Bundle?) {}

    override fun initData() {}
    override fun initToolbar(toolbar: Toolbar, popBackCallBack: () -> Unit) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener {
            popBackCallBack()
            finish()
        }
    }

    /**
     * binding 绑定 ViewModel 操作
     */
    override fun setBindingVariable() {}

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
//        GlideApp.with(this).onTrimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
//        GlideApp.with(this).onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.unbind()
        _binding = null
        AppManager.instance.removeActivity(this)
    }
}