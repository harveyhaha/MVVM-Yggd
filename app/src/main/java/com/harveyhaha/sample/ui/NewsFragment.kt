package com.harveyhaha.sample.ui

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
import com.harveyhaha.sample.R
import com.harveyhaha.sample.busevent.DrawerEvent
import com.harveyhaha.sample.databinding.FragmentNewsBinding
import com.harveyhaha.sample.ui.adapter.NewsAdapter
import com.harveyhaha.yggd.base.BaseFragment
import com.harveyhaha.yggd.weiget.DividerItemDecoration
import com.wtf.commonlib.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import timber.log.Timber

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-8 下午2:15
 */
@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>(), SwipeRefreshLayout.OnRefreshListener,
    OnLoadMoreListener {

    val args: NewsFragmentArgs by navArgs()
    lateinit var newsAdapter: NewsAdapter
    override fun initContentView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int {
        return R.layout.fragment_news
    }

    override fun initParam() {
        super.initParam()
        viewModel.username = args.username
        Timber.i("login user:%s", args.username)
    }

    override fun setBindingVariable() {
        super.setBindingVariable()
        binding.viewmodel = viewModel
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        initToolbar()
        initEventUi()
    }

    override fun initData() {
        super.initData()
    }

    private fun initToolbar() {
        getAppCompatActivity().setSupportActionBar(binding.toolbar)
        getAppCompatActivity().supportActionBar?.let { actionbar ->
            actionbar.title = getString(R.string.news)
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        binding.toolbar.setNavigationOnClickListener {
            EventBus.getDefault().post(DrawerEvent(true))
        }
    }

    private fun initEventUi() {
        newsAdapter = NewsAdapter()
        newsAdapter.loadMoreModule?.setOnLoadMoreListener(this)
        newsAdapter.adapterAnimation = ScaleInAnimation()
        newsAdapter.setOnItemClickListener { _, _, position ->
            Toast.makeText(requireContext(), "position$position", Toast.LENGTH_SHORT).show()
        }
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189))
        binding.recyclerView.adapter = newsAdapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        viewModel.let { viewModel ->
            viewModel.eventDataList.observe(this, Observer {
                if (it != null) {
                    newsAdapter.setNewData(it)
                }
            })
            viewModel.recycleLoadMoreStatus.observe(this, Observer {
                when (it.LoadMoreStatus) {
                    LoadMoreStatus.Loading -> {
                    }
                    LoadMoreStatus.Complete -> {
                        newsAdapter.loadMoreModule?.loadMoreComplete()
                    }
                    LoadMoreStatus.End -> {
                        newsAdapter.loadMoreModule?.loadMoreEnd(it.isEnable)
                    }
                    LoadMoreStatus.Fail -> {
                        newsAdapter.loadMoreModule?.loadMoreFail()
                    }
                }
                newsAdapter.loadMoreModule?.isEnableLoadMore = it.isEnable
            })
            onRefresh()
        }
    }

    override fun onRefresh() {
        Timber.i("onRefresh")
        viewModel.getPrivateReceiveEvents()
    }

    override fun onLoadMore() {
        Timber.i("onLoadMore")
        viewModel.getPrivateReceiveEventsLoadMore()
    }
}