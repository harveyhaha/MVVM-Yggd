package com.wtf.sample.utils

import android.content.Context
import com.wtf.sample.R
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


/**
 * Created by wtf on 2/1/20 10:29 AM.
 */


class StringUtils {

    companion object {
        private val dateRegexMap =
            hashMapOf<Locale, String>(Locale.CANADA to "yyyy-MM-dd", Locale.ENGLISH to "MMM d, yyyy")
        private const val MILLIS_LIMIT: Float = 1000f
        private const val SECONDS_LIMIT = 60 * MILLIS_LIMIT //60000
        private const val MINUTES_LIMIT = 60 * SECONDS_LIMIT //3600000
        private const val HOURS_LIMIT = 24 * MINUTES_LIMIT //216000000
        private const val DAYS_LIMIT = 30 * HOURS_LIMIT

        fun getNewsTime(context: Context, date: Date): String {
            val subTime = System.currentTimeMillis() - date.time
            Timber.i("时间%d", subTime)
            when {
                subTime < MILLIS_LIMIT -> {
                    return context.getString(R.string.just_now)
                }
                subTime < SECONDS_LIMIT -> {
                    return (subTime / MILLIS_LIMIT).roundToInt().toString() + context.getString(R.string.seconds_ago)
                }
                subTime < MINUTES_LIMIT -> {
                    return (subTime / SECONDS_LIMIT).roundToInt().toString() + context.getString(R.string.minutes_ago)
                }
                subTime < HOURS_LIMIT -> {
                    return (subTime / MINUTES_LIMIT).roundToInt().toString() + context.getString(R.string.hours_ago)
                }
                subTime < DAYS_LIMIT -> {
                    return (subTime / HOURS_LIMIT).roundToInt().toString() + context.getString(R.string.days_ago)
                }
                else -> {
                    return getDateString(context, date)
                }
            }
        }

        private fun getDateString(context: Context, date: Date): String {
            val locale = getLocale(SpUtils.getLanguage(context))
            val regex: String = dateRegexMap[locale] ?: "yyyy-MM-dd"
            val format = SimpleDateFormat(regex, locale)
            return format.format(date)
        }

        private fun getLocale(language: String): Locale {
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