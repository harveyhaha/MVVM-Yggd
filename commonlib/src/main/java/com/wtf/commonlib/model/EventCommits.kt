package com.wtf.commonlib.model

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-20 下午3:24
 */
data class EventCommits constructor(
    var sha: String,
    var author: User,
    var message: String,
    var distinct: Boolean,
    var url: String
)