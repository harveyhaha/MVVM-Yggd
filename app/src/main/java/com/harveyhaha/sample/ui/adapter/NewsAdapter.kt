package com.harveyhaha.sample.ui.adapter

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.text.style.TextAppearanceSpan
import android.view.View
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.harveyhaha.sample.R
import com.harveyhaha.sample.databinding.FragmentNewItemLayoutBinding
import com.harveyhaha.sample.utils.AppUtils
import com.harveyhaha.sample.utils.StringUtils
import com.harveyhaha.yggd.base.GlideApp
import com.wtf.commonlib.model.Event
import com.wtf.commonlib.model.EventPayloadRefType
import com.wtf.commonlib.model.EventType
import java.util.regex.Matcher

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
            if (item?.created_at != null)
                binding.timeAgoTv.text = StringUtils.getNewsTime(context, item.created_at)
            setContentString(binding, item)
        }
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<FragmentNewItemLayoutBinding>(viewHolder.itemView)
    }

    private fun setContentString(binding: FragmentNewItemLayoutBinding, item: Event?): SpannableStringBuilder {
        var contentText = ""
        binding.pushedGroup.visibility = View.GONE
        when (item?.type) {
            EventType.ForkEvent -> {
                contentText = context.getString(R.string.fork_from, item.repo.name, item.payload.forkee.full_name)
            }
            EventType.WatchEvent -> {
                contentText = context.getString(R.string.watch_from, item.repo.name)
            }
            EventType.CreateEvent -> {
                when (item.payload.ref_type) {
                    EventPayloadRefType.BRANCH -> {
                        contentText = context.getString(R.string.create_branch, item.payload.ref, item.repo.name)
                    }
                    EventPayloadRefType.TAG -> {
                        contentText = context.getString(R.string.create_tag, item.payload.ref, item.repo.name)
                    }
                }
            }
            EventType.PushEvent -> {
                contentText = context.getString(R.string.pushed_to, item.repo.name)
                binding.pushedGroup.visibility = View.VISIBLE
                val pushedCommitTitleSpan = SpannableStringBuilder(
                    context.getString(
                        R.string.pushed_total_commit, item.payload.size.toString(),
                        item.payload.getBranch()
                    )
                )
                pushedCommitTitleSpan.setSpan(
                    StyleSpan(Typeface.BOLD), pushedCommitTitleSpan.lastIndexOf(
                        item.payload.getBranch()
                    ), pushedCommitTitleSpan.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                binding.pushedCommitTitleTv.text = pushedCommitTitleSpan
                val commitContentListSize = if (item.payload.size > 2) 2 else item.payload.size
                val spannableStringBuilder = SpannableStringBuilder()
                for (i in 0 until commitContentListSize) {
                    val spannableStringBuilderChild = SpannableStringBuilder()
                    spannableStringBuilderChild.append(item.payload.commits[i].sha.substring(0, 7))
                    spannableStringBuilderChild.append(" ")
                    spannableStringBuilderChild.append(item.payload.commits[i].message)
                    if (i != (commitContentListSize - 1)) {
                        spannableStringBuilderChild.append("\n")
                    }
                    spannableStringBuilderChild.setSpan(
                        TextAppearanceSpan(context, R.style.textLinkStyle), 0, 7,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spannableStringBuilder.append(spannableStringBuilderChild)
                }
                binding.pushedCommitListTv.text = spannableStringBuilder

                if (item.payload.size > 2) {
                    binding.pushedMoreCommitTv.visibility = View.VISIBLE
                    binding.pushedMoreCommitTv.text =
                        context.getString(R.string.pushed_more_commit, (item.payload.size - 2).toString())
                } else {
                    binding.pushedMoreCommitTv.visibility = View.GONE
                }
            }
        }
        val span = SpannableStringBuilder(contentText)
        val matcher: Matcher = AppUtils.REPO_FULL_NAME_PATTERN.matcher(contentText)
        while (matcher.find()) {
            span.setSpan(
                StyleSpan(Typeface.BOLD), matcher.start(), matcher.end(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        binding.contentTv.text = span
        return span
    }
}