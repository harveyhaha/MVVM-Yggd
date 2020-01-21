package com.wtf.sample.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.animation.ScaleInAnimation
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.chad.library.adapter.base.loadmore.LoadMoreStatus
import com.wtf.sample.R
import com.wtf.sample.databinding.FragmentNewsBinding
import com.wtf.sample.ui.adapter.NewsAdapter
import com.wtf.sample.viewmodels.NewsViewModel
import com.wtf.yggd.base.BaseFragment
import com.wtf.yggd.weiget.DividerItemDecoration
import timber.log.Timber


/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-8 下午2:15
 */
class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>(), SwipeRefreshLayout.OnRefreshListener,
    OnLoadMoreListener {

    val args: NewsFragmentArgs by navArgs()
    lateinit var adapter: NewsAdapter
    override fun initContentView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int {
        return R.layout.fragment_news
    }

    override fun initParam() {
        super.initParam()
        viewModel?.username = args.username
        Timber.i("login user:%s", args.username)
    }

    override fun setBindingVariable() {
        super.setBindingVariable()
        binding.viewmodel = viewModel
    }

    override fun initView() {
        super.initView()
        initToolbar()
        initEventUi()
    }

    override fun initData() {
        super.initData()
    }

    private fun initToolbar() {
        getAppCompatActivity().setSupportActionBar(binding.toolbar)
        getAppCompatActivity().supportActionBar?.title = "main"
    }

    private fun initEventUi() {
        adapter = NewsAdapter()
        adapter.loadMoreModule?.let { loadMoreModule ->
            loadMoreModule.isEnableLoadMore = true
            loadMoreModule.setOnLoadMoreListener(this)
            loadMoreModule.isAutoLoadMore = true
        }
        adapter.adapterAnimation = ScaleInAnimation()
        adapter.setOnItemClickListener { _, _, position ->
            Toast.makeText(requireContext(), "posion$position", Toast.LENGTH_SHORT).show()
        }
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189))
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        viewModel?.let { viewModel ->
            viewModel.eventDataList.observe(this, Observer {
                if (it != null) {
                    adapter.setNewData(it)
                }
            })
            viewModel.recycleLoadMoreStatus.observe(this, Observer {
                when (it.LoadMoreStatus) {
                    LoadMoreStatus.Loading -> {
                    }
                    LoadMoreStatus.Complete -> {
                        adapter.loadMoreModule?.loadMoreComplete()
                    }
                    LoadMoreStatus.End -> {
                        adapter.loadMoreModule?.loadMoreEnd(it.isEnable)
                    }
                    LoadMoreStatus.Fail -> {
                        adapter.loadMoreModule?.loadMoreFail()
                    }
                }
            })
            viewModel.getPrivateReceiveEvents(viewModel.page)
        }
    }

    override fun onRefresh() {
        Timber.i("onRefresh")
        viewModel?.let { viewModel ->
            viewModel.page = 1
            viewModel.getPrivateReceiveEvents()
        }
    }

    override fun onLoadMore() {
        Timber.i("onLoadMore")
        viewModel?.let { viewModel ->
            viewModel.getPrivateReceiveEvents(viewModel.page)
        }
    }
}