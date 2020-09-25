package com.wtf.commonlib.model

import com.harveyhaha.sample.db.entity.UserEntity

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-20 下午2:54
 */
data class User constructor(
    val login: String,
    val id: String,
    val node_id: String,
    val avatar_url: String,
    val url: String,
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
) {
    fun toUserEntity(): UserEntity {
        return UserEntity(
            login,
            id,
            node_id,
            avatar_url,
            url,
            type,
            name,
            company,
            blog,
            location,
            email,
            bio,
            public_repos,
            public_gists,
            followers,
            following,
            created_at,
            updated_at,
            two_factor_authentication
        )
    }
}