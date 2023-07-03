package com.harveyhaha.yggd.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.fragment.findNavController
import com.harveyhaha.yggd.base.listeners.IBaseFragmentView
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:58
 */
abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel> : Fragment(), IBaseFragmentView {
    var _binding: V? = null
    val binding get() = _binding!!
    open lateinit var viewModel: VM
    var toolbarInit = false

    @Suppress("UNCHECKED_CAST")
    @NonNull
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            initContentView(inflater, container, savedInstanceState),
            container,
            false
        )
        val type: ParameterizedType = javaClass.genericSuperclass as ParameterizedType
        val actualTypeArguments: Array<Type> = type.actualTypeArguments
        val modelClass: Class<VM> = actualTypeArguments[1] as Class<VM>
        viewModel = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(modelClass)
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
    override fun isActivityViewModel(): Boolean {
        return false
    }

    override fun initParam() {}

    override fun setBindingVariable() {}

    override fun initView(savedInstanceState: Bundle?) {}

    override fun initData() {}

    override fun initToolbar(toolbar: Toolbar, popBackCallBack: () -> Unit) {
        toolbarInit = true
        getAppCompatActivity().setSupportActionBar(toolbar)
        getAppCompatActivity().supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        getAppCompatActivity().supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener {
            popBackCallBack()
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
        _binding = null
        if (toolbarInit) {
            getAppCompatActivity().setSupportActionBar(null)
        }
    }

    override fun getAppCompatActivity(): AppCompatActivity {
        return requireActivity() as AppCompatActivity
    }

    override fun getViewModelStore(): ViewModelStore {
        return if (isActivityViewModel()) requireActivity().viewModelStore else super.getViewModelStore()
    }

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        return if (isActivityViewModel()) requireActivity().defaultViewModelProviderFactory else super.getDefaultViewModelProviderFactory()
    }
}