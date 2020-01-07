package com.wtf.yggd.base

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-7 上午10:40
 */
@GlideModule
class GlideAppModule : AppGlideModule() {
    override fun isManifestParsingEnabled() = false
}