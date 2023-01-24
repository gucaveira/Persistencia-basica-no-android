package dominando.android.persistencia

import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class ConfigFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

    private lateinit var editPrefCity: EditTextPreference
    private lateinit var listPrefSocialNetworks: ListPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        setPreferencesFromResource(R.xml.app_preferences, null)
        editPrefCity = findPreference(getString(R.string.pref_city))!!
        listPrefSocialNetworks = findPreference(getString(R.string.pref_social_network))!!
        fillSummary(editPrefCity)
        fillSummary(listPrefSocialNetworks)
    }

    private fun fillSummary(preference: Preference) {
        preference.onPreferenceChangeListener = this
        val pref = activity?.let { PreferenceManager.getDefaultSharedPreferences(it) }
        val value = pref?.getString(preference.key, "") ?: ""
        onPreferenceChange(preference, value)
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any): Boolean {
        val stringValue = newValue.toString()
        when (preference) {
            listPrefSocialNetworks -> {
                val index = listPrefSocialNetworks.findIndexOfValue(stringValue)
                if (index >= 0) {
                    listPrefSocialNetworks.summary = listPrefSocialNetworks.entries[index]
                }
            }
            editPrefCity -> {
                editPrefCity.summary = stringValue
            }
        }
        return true
    }
}