package com.wtf.sample.model

import com.wtf.sample.db.entity.UserEntity

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-20 下午2:45
 */
data class Event constructor(
    var id: String,
    var type: String,
    var actor: UserEntity,
    var repo: Repository,
    var payload: EventPayload,
    var public: Boolean,
    var create_at: String
)