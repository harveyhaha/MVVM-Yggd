package com.wtf.commonlib.model

import androidx.annotation.StringDef
import com.wtf.commonlib.model.EventPayloadRefType.Companion.BRANCH
import com.wtf.commonlib.model.EventPayloadRefType.Companion.TAG

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     2/5/20 12:34 PM
 */
@StringDef(BRANCH, TAG)
@Retention(AnnotationRetention.SOURCE)
annotation class EventPayloadRefType {
    companion object {
        const val BRANCH = "branch"
        const val TAG = "tag"
    }
}