package com.wtf.yggd.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.AndroidInjection
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:58
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    IBaseActivity {
    open lateinit var binding: V
    open var viewModel: VM? = null
//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
        //页面接受的参数方法
        initParam()
        initViewDataBinding(savedInstanceState)
    }

    private fun initViewDataBinding(savedInstanceState: Bundle?) {
//        newsListViewModel = viewModelFactory.create(NewsListViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState))
        viewModel = initViewModel()
        viewModel.let {
            val type: ParameterizedType = javaClass.genericSuperclass as ParameterizedType
            val actualTypeArguments: Array<Type> = type.actualTypeArguments
            val modelClass: Class<VM> = actualTypeArguments[0] as Class<VM>
//            viewModel = viewModelFactory.create(modelClass)
        }
        setBindingVariable()
        //让ViewModel拥有View的生命周期感应
//        lifecycle.addObserver(viewModel)
    }

    abstract fun initContentView(savedInstanceState: Bundle?): Int

    open fun initViewModel(): VM? {
        return null
    }

    override fun initParam() {}

    override fun initView() {}

    override fun initData() {}

    /**
     * binding 绑定 ViewModel 操作
     */
    override fun setBindingVariable() {}

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.removeActivity(this)
    }
}