package com.wtf.sample.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wtf.sample.R
import com.wtf.sample.databinding.FragmentNewItemLayoutBinding
import com.wtf.sample.model.Event
import com.wtf.yggd.base.GlideApp

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-20 下午5:33
 */
class NewsAdapter : BaseQuickAdapter<Event, BaseViewHolder>(R.layout.fragment_new_item_layout), LoadMoreModule {

    override fun convert(helper: BaseViewHolder, item: Event?) {
        val binding = helper.getBinding<FragmentNewItemLayoutBinding>()
        binding?.let {
            GlideApp.with(context).load(item?.actor?.avatar_url).into(binding.avatarIv)
            binding.nameTv.text = item?.actor?.login
        }
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<FragmentNewItemLayoutBinding>(viewHolder.itemView)
    }
}