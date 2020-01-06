package com.wtf.yggd.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:58
 */
abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    protected var binding: V? = null
    protected var viewModel: VM? = null
    abstract val layoutId: Int
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }
}