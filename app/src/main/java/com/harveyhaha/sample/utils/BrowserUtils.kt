package com.harveyhaha.sample.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.widget.Toast
import com.harveyhaha.sample.R
import timber.log.Timber
import java.util.*

/**
 * @Description:    浏览器工具类
 * @Author:         harveyhaha
 * @CreateDate:     20-1-17 上午9:24
 */
class BrowserUtils {
    companion object {
        val instance: BrowserUtils by lazy { BrowserUtils() }
    }

    fun openInBrowser(context: Context, url: String) {
        Timber.i("url %s ", url)
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri).addCategory(Intent.CATEGORY_BROWSABLE)
        val activityChooseIntent: Intent?
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activityChooseIntent = createActivityChooserIntent(
            context,
            intent,
            uri,
            null
        )
        if (activityChooseIntent != null) {
            context.startActivity(activityChooseIntent)
        } else {
            Toast.makeText(
                context,
                R.string.login_error_cannot_find_browser_client,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun createActivityChooserIntent(
        context: Context,
        intent: Intent,
        uri: Uri,
        ignorePackageList: List<String>?
    ): Intent? {
        val pm = context.packageManager
        val activities = pm.queryIntentActivities(
            intent,
            PackageManager.MATCH_DEFAULT_ONLY
        )
        val chooserIntents = ArrayList<Intent>()
        val ourPackageName = context.packageName
        Collections.sort(activities, ResolveInfo.DisplayNameComparator(pm))
        for (resInfo in activities) {
            val info = resInfo.activityInfo
            if (!info.enabled || !info.exported) {
                continue
            }
            if (info.packageName == ourPackageName) {
                continue
            }
            if (ignorePackageList != null && ignorePackageList.contains(info.packageName)) {
                continue
            }
            val targetIntent = Intent(intent)
            targetIntent.setPackage(info.packageName)
            targetIntent.setDataAndType(uri, intent.type)
            chooserIntents.add(targetIntent)
        }
        if (chooserIntents.isEmpty()) {
            return null
        }
        val lastIntent = chooserIntents.removeAt(chooserIntents.size - 1)
        if (chooserIntents.isEmpty()) { // there was only one, no need to showImage the chooser
            return lastIntent
        }
        val chooserIntent = Intent.createChooser(lastIntent, null)
        chooserIntent.putExtra(
            Intent.EXTRA_INITIAL_INTENTS,
            chooserIntents.toTypedArray()
        )
        return chooserIntent
    }
}