package rk.enkidu.githubusers20.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SettingsViewModel (private val pref: SettingPreferences) : ViewModel() {
    fun getThemeSettings() : LiveData<Boolean> {
        return pref.getThemeSettings().asLiveData()
    }

    fun saveThemeSettings(isDarkModeActive: Boolean){
        viewModelScope.launch {
            pref.saveThemeSettings(isDarkModeActive)
        }
    }

}