package com.wtf.sample.model

import androidx.room.Entity

/**
 * Created by wtf on 1/13/20 8:17 PM.
 */
@Entity
data class AuthToken(
    val token: String,
    val loginId: Boolean
)