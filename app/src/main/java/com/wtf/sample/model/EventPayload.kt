package com.wtf.sample.model

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-20 下午3:21
 */
data class EventPayload constructor(
    var push_id: String,
    var size: Int,
    var distinct_size: Int,
    var ref: String,
    var head: String,
    var before: String,
    var commits: ArrayList<EventCommits>,
    var forkee: EventForkee
)