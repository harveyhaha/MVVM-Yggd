package com.wtf.sample.model

import androidx.annotation.StringDef
import com.wtf.sample.model.EventPayloadRefType.Companion.BRANCH
import com.wtf.sample.model.EventPayloadRefType.Companion.TAG

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