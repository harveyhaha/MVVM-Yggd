package com.wtf.sample.model

import java.util.*

/**
 * Created by wtf on 2/1/20 10:31 PM.
 */
data class EventForkee constructor(
    var id: String,
    var node_id: String,
    var name: String,
    var full_name: String,
    var private: Boolean,
    var owner: User,
    var html_url: String,
    var description: String,
    var fork: Boolean,
    var url: String,
    var fork_url: String,
    var created_at: Date,
    var updated_at: Date,
    var pushed_at: Date,
    var forks: Int,
    var open_issues: Int,
    var watchers: Int,
    var default_branch: String,
    var public: Boolean
)