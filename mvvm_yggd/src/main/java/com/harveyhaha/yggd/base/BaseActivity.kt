package com.harveyhaha.yggd.base

// import com.harveyhaha.yggd.di.ViewModelFactory
// import dagger.android.AndroidInjection
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.harveyhaha.yggd.base.listeners.IBaseActivityView
import com.harveyhaha.yggd.utils.AppManager
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

// import javax.inject.Inject

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:58
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    IBaseActivityView {
    open lateinit var binding: V
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
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState))
        binding.lifecycleOwner = this
        val type: ParameterizedType = javaClass.genericSuperclass as ParameterizedType
        val actualTypeArguments: Array<Type> = type.actualTypeArguments
        val modelClass: Class<VM> = actualTypeArguments[1] as Class<VM>
        viewModel = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(modelClass)
        setBindingVariable()
    }

    abstract override fun initContentView(savedInstanceState: Bundle?): Int

    override fun isActivityViewModel(): Boolean {
        return true
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
//        GlideApp.with(this).onTrimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
//        GlideApp.with(this).onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
        AppManager.instance.removeActivity(this)
    }
}