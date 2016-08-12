package jp.cordea.butler.model

import android.content.Context
import android.preference.PreferenceManager

/**
 * Created by Yoshihiro Tanaka on 2016/07/19.
 */
class UserPreference(val jenkinsUrl: String?, val username: String?, val apiToken: String?, val isUserListVisible: Boolean) {

    companion object {

        private val urlKey = "UrlKey"
        private val usernameKey = "UsernameKey"
        private val apiTokenKey = "ApiTokenKey"
        private val isUserListVisibleKey = "IsUserListVisibleKey"

        fun save(context: Context, jenkinsUrl: String) {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = pref.edit()
            editor.putString(urlKey, jenkinsUrl)
            editor.apply()
        }

        fun save(context: Context, jenkinsUrl: String, username: String, apiToken: String) {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = pref.edit()
            editor.putString(urlKey, jenkinsUrl)
            editor.putString(usernameKey, username)
            editor.putString(apiTokenKey, apiToken)
            editor.apply()
        }

        fun isUserListVisible(context: Context, isUserListVisible: Boolean) {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            pref.edit().putBoolean(isUserListVisibleKey, isUserListVisible).apply()
        }

        fun load(context: Context): UserPreference {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            val url = pref.getString(urlKey, null)
            val name = pref.getString(usernameKey, null)
            val token = pref.getString(apiTokenKey, null)
            val isUserListVisible = pref.getBoolean(isUserListVisibleKey, true)
            return UserPreference(url, name, token, isUserListVisible)
        }

        fun clear(context: Context) {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            pref.edit().clear().apply()
        }

    }

}