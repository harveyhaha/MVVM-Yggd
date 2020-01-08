package com.wtf.yggd.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.wtf.yggd.base.listeners.BaseFragmentView
import com.wtf.yggd.di.ViewModelFactory
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
    BaseFragmentView {
    open lateinit var binding: V
    open var viewModel: VM? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParam()
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
        return binding.root
    }

    abstract override fun initContentView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int

    private fun initViewModel(): VM? {
        return null
    }
}