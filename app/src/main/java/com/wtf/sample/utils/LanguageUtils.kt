package com.wtf.sample.utils

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import timber.log.Timber
import java.util.*

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     2/2/20 10:12 PM
 */
class LanguageUtils {
    companion object {
        fun updateAppLanguage(context: Context) {
            val language = SpUtils.getLanguage(context)
            Timber.i("now language %s", language)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                updateLanguage(context, language)
            }
            updateResourcesLegacy(context, language)
        }

        @TargetApi(Build.VERSION_CODES.N)
        fun updateLanguage(context: Context, language: String): Context {
            val locale = getLocale(language)
            Locale.setDefault(locale)
            val configuration = context.resources.configuration
            configuration.setLocale(locale)
            context.createConfigurationContext(configuration)
            return context
        }

        @Suppress("DEPRECATION")
        private fun updateResourcesLegacy(context: Context, language: String) {
            val locale = getLocale(language)
            Locale.setDefault(locale)
            val resources: Resources = context.resources
            val configuration: Configuration = resources.configuration
            configuration.locale = locale
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }

        fun getLocale(language: String): Locale {
            val locale: Locale
            val array = language.split("-").toTypedArray()
            locale = if (array.size > 1) { //zh-rCN, zh-rTW", pt-rPT, etc... remove the 'r'
                val country = array[1].replaceFirst("r".toRegex(), "")
                Locale(array[0], country)
            } else {
                Locale(language)
            }
            return locale
        }
    }
}