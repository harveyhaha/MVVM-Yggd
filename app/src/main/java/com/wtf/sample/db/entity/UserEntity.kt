package com.wtf.sample.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

/**
 * Created by wtf on 1/13/20 7:57 PM.
 */
@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @Nonnull
    val login: String,
    val id: String,
    val node_id: String,
    val avatar_url: String,
    val type: String,
    val name: String,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    val public_repos: String?,
    val public_gists: String?,
    val followers: String?,
    val following: String?,
    val created_at: String?,
    val updated_at: String?,
    val two_factor_authentication: Boolean
)