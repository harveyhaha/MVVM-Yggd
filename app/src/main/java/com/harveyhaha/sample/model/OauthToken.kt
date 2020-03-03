package com.harveyhaha.sample.model

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-17 下午3:29
 */
data class OauthToken(
    var access_token: String,
    var token_type: String,
    var scope: String
)