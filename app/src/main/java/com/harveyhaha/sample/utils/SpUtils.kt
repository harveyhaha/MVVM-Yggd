package com.harveyhaha.sample.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

/**
 * Created by wtf on 2/1/20 3:50 PM.
 */
class SpUtils {
    companion object {
        private const val LANGUAGE = "language"

        fun getLanguage(context: Context): String {
            return getSp(context).getString(LANGUAGE, "zh-rCN").toString()
        }

        fun setLanguage(context: Context, language: String) {
            getSp(context).edit().putString(LANGUAGE, language).apply()
        }

        private fun getSp(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }
    }
}