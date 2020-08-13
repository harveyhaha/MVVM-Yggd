package com.harveyhaha.yggd.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.harveyhaha.yggd.base.listeners.IBaseFragmentView
import com.harveyhaha.yggd.di.ViewModelFactory
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
open abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel> : Fragment(),
    IBaseFragmentView {
    open lateinit var binding: V
    open lateinit var viewModel: VM

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
        val type: ParameterizedType = javaClass.genericSuperclass as ParameterizedType
        val actualTypeArguments: Array<Type> = type.actualTypeArguments
        val modelClass: Class<VM> = actualTypeArguments[1] as Class<VM>
        viewModel = ViewModelProvider(getViewModelStoreOwner(), viewModelFactory).get(modelClass)
        initParam()
        setBindingVariable()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
        initData()
    }

    abstract override fun initContentView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int

    /**
     * fragment可通过返回activity ViewModelStoreOwner 将ViewModel建在Activity上
     */
    override fun getViewModelStoreOwner(): ViewModelStoreOwner {
        return this
    }

    override fun initParam() {}

    override fun setBindingVariable() {}

    override fun initView(savedInstanceState: Bundle?) {}

    override fun initData() {}
    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }

    override fun getAppCompatActivity(): AppCompatActivity {
        return context as AppCompatActivity
    }
}