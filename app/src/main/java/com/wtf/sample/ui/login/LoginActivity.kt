package com.wtf.sample.ui.login

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.widget.ViewUtils
import androidx.lifecycle.Observer
import com.wtf.sample.R
import com.wtf.sample.databinding.ActivityLoginBinding
import com.wtf.sample.ui.MainActivity
import com.wtf.sample.viewmodels.LoginViewModel
import com.wtf.yggd.base.BaseActivity
import timber.log.Timber

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-8 下午2:15
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        super.initView()
        Timber.i(viewModel.toString())
        viewModel?.loginUser?.observe(this, Observer {
            if (it != null) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user", it)
                startActivity(intent)
                finish()
            }
        })
    }

    override fun setBindingVariable() {
        super.setBindingVariable()
        binding.viewmodel = viewModel
    }

    fun onLoginClick(view: View) {
        if (loginCheck()) {
            viewModel?.basicLogin()
        }
    }

    fun onBrowserLoginClick(view: View) {

    }

    private fun loginCheck(): Boolean {
        var checkValid = true
        if (TextUtils.isEmpty(viewModel?.userName?.get()?.trim())) {
            checkValid = false
            binding.usernameTextil.error = getString(R.string.user_name_warning)
        } else
            binding.usernameTextil.error = null
        if (TextUtils.isEmpty(viewModel?.token?.get()?.trim())) {
            checkValid = false
            binding.passwordTextil.error = getString(R.string.password_warning)
        } else
            binding.passwordTextil.error = null
        return checkValid
    }

    fun openInCustomTabsOrBrowser(@NonNull context: Context, @NonNull url: String) {
        var url = url
        if (TextUtils.isEmpty(url.trim())) {
            Toasty.warning(context, context.getString(R.string.invalid_url), Toast.LENGTH_LONG)
                .show()
            return
        }
        //check http prefix
        if (!url.contains("//")) {
            url = "http://$url"
        }
        var customTabsPackageName: String?
        if (PrefUtils.isCustomTabsEnable() &&
            CustomTabsHelper.INSTANCE.getBestPackageName(context).also({
                customTabsPackageName = it
            }) != null
        ) {
            val backIconBitmap: Bitmap =
                ViewUtils.getBitmapFromResource(context, R.drawable.ic_arrow_back_title)
            val shareIntent =
                Intent(context.applicationContext, ShareBroadcastReceiver::class.java)
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val sharePendingIntent = PendingIntent.getBroadcast(
                context.applicationContext, 0, shareIntent, 0
            )
            val copyIntent =
                Intent(context.applicationContext, CopyBroadcastReceiver::class.java)
            copyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val copyPendingIntent = PendingIntent.getBroadcast(
                context.applicationContext, 0, copyIntent, 0
            )
            val customTabsIntent: CustomTabsIntent = Builder()
                .setToolbarColor(ViewUtils.getPrimaryColor(context))
                .setCloseButtonIcon(backIconBitmap)
                .setShowTitle(true)
                .addMenuItem(context.getString(R.string.share), sharePendingIntent)
                .addMenuItem(
                    context.getString(R.string.copy_url),
                    copyPendingIntent
                ) //                    .setStartAnimations(context, R.anim.slide_in_right, R.anim.slide_out_left)
//                    .setExitAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .build()
            customTabsIntent.intent.setPackage(customTabsPackageName)
            customTabsIntent.launchUrl(context, Uri.parse(url))
            if (PrefUtils.isCustomTabsTipsEnable()) {
                Toasty.info(
                    context,
                    context.getString(R.string.use_custom_tabs_tips),
                    Toast.LENGTH_LONG
                ).show()
                PrefUtils.set(PrefUtils.CUSTOM_TABS_TIPS_ENABLE, false)
            }
        } else {
            com.thirtydegreesray.openhub.util.AppOpener.openInBrowser(context, url)
        }
    }

    fun openInBrowser(@NonNull context: Context, @NonNull url: String?) {
        val uri = Uri.parse(url)
        var intent = Intent(Intent.ACTION_VIEW, uri).addCategory(Intent.CATEGORY_BROWSABLE)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent = AppOpener.createActivityChooserIntent(
            context,
            intent,
            uri,
            AppOpener.VIEW_IGNORE_PACKAGE
        )
        if (intent != null) {
            context.startActivity(intent)
        } else {
            Toasty.warning(
                context,
                context.getString(R.string.no_browser_clients),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}