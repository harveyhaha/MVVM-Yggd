package com.harveyhaha.sample.model

import com.harveyhaha.sample.db.entity.UserEntity
import java.util.*

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-20 下午2:45
 */
data class Event constructor(
    var id: String,
    @EventType
    var type: String,
    var actor: UserEntity,
    var repo: Repository,
    var payload: EventPayload,
    var public: Boolean,
    var created_at: Date
)