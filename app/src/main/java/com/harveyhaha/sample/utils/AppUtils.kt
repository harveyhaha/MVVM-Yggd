package com.harveyhaha.sample.utils

import java.util.regex.Pattern

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     2/2/20 7:12 PM
 */
class AppUtils {
    companion object{
        val REPO_FULL_NAME_PATTERN =
            Pattern.compile("([a-z]|[A-Z]|\\d|-)*/([a-z]|[A-Z]|\\d|-|\\.|_)*")
    }
}