package com.wtf.yggd.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.wtf.yggd.base.listeners.IBaseFragmentView
import com.wtf.yggd.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject


/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:58
 */
abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel> : Fragment(),
    IBaseFragmentView {
    open lateinit var binding: V
    open var viewModel: VM? = null
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    @NonNull
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            initContentView(inflater, container, savedInstanceState),
            container,
            false
        )
        viewModel = initViewModel()
        if (viewModel == null) {
            val type: ParameterizedType = javaClass.genericSuperclass as ParameterizedType
            val actualTypeArguments: Array<Type> = type.actualTypeArguments
            val modelClass: Class<VM> = actualTypeArguments[1] as Class<VM>
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(modelClass)
        }
        initParam()
        setBindingVariable()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    abstract override fun initContentView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int

    /**
     * 默认 依托fragment建立ViewModel,如果想引入ParentActivity一样的ViewModel,则可依照如下代码自定义
     * return activity?.let {
     *    ViewModelProviders.of(it, viewModelFactory).get(MainViewModel::class.java)
     * }
     */
    open fun initViewModel(): VM? {
        return null
    }

    override fun initParam() {}

    override fun setBindingVariable() {}

    override fun initView() {}

    override fun initData() {}
    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }

    override fun getAppCompatActivity(): AppCompatActivity {
        return context as AppCompatActivity
    }
}