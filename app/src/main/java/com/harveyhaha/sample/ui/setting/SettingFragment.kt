package com.harveyhaha.sample.ui.setting

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.harveyhaha.sample.R
import com.harveyhaha.sample.busevent.RecreateEvent
import com.harveyhaha.sample.utils.SpUtils
import org.greenrobot.eventbus.EventBus

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     2/2/20 3:26 PM
 */
class SettingFragment : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener {
    companion object {
        private const val LANGUAGE_KEY = "language"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.fragment_setting_prefrence, rootKey)
        findPreference<Preference>(LANGUAGE_KEY)?.onPreferenceClickListener = this
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        when (preference?.key) {
            LANGUAGE_KEY -> {
                showLanguageDialog()
                return true
            }
        }
        return false
    }

    private fun showLanguageDialog() {
        val languageList = resources.getStringArray(R.array.language_id_array)
        MaterialDialog(this.requireActivity())
            .title(R.string.language)
            .listItems(R.array.language_array) { dialog: MaterialDialog, index: Int, _ ->
                val languageId = languageList[index]
                SpUtils.setLanguage(requireContext(), languageId)
                dialog.dismiss()
                EventBus.getDefault().post(RecreateEvent())
            }.show()
    }
}