package com.wtf.commonlib.model

import com.chad.library.adapter.base.loadmore.LoadMoreStatus

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-21 下午5:38
 */
data class RecycleLoadMoreStatus constructor(var LoadMoreStatus: LoadMoreStatus?, var isEnable: Boolean = true)