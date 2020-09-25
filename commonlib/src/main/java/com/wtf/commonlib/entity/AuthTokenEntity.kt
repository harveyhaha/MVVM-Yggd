package com.wtf.commonlib.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-14 上午9:40
 */
@Entity(tableName = "auth_token")
data class AuthTokenEntity(
    @PrimaryKey
    @NonNull
    val login: String,
    val token: String,
    var isLogin: Boolean = false
)