package com.wtf.sample.utils

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
            return getSp(context).getString(LANGUAGE, "en").toString()
        }

        private fun getSp(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }
    }
}