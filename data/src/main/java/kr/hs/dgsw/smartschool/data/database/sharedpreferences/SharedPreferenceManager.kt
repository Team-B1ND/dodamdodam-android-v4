package kr.hs.dgsw.smartschool.data.database.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceManager {

    private const val PREF_ACCOUNT_NAME = "account"
    private const val PREF_DARK_MODE = "pref_daylight"
    private const val PREF_IS_LOGIN = "is_login"

    fun getIsLogin(context: Context): Boolean {
        return getDefaultSharedPreferences(context).getBoolean(PREF_IS_LOGIN, false)
    }

    fun login(context: Context) {
        getDefaultSharedPreferences(context).edit().putBoolean(PREF_IS_LOGIN, true).apply()
    }

    fun logout(context: Context) {
        getDefaultSharedPreferences(context).edit().putBoolean(PREF_IS_LOGIN, false).apply()
    }

    fun getDayLight(context: Context) : Boolean {
        return getDefaultSharedPreferences(context).getBoolean(PREF_DARK_MODE, true)
    }

    fun setDayLight(context: Context, value: Boolean) {
        getDefaultSharedPreferences(context).edit().putBoolean(PREF_DARK_MODE, value).apply()
    }

    fun insertAccountName(context: Context, accountName: String) {
        getDefaultSharedPreferences(context).edit().putString(PREF_ACCOUNT_NAME, accountName).apply()
    }

    fun deleteAccountName(context: Context) {
        getDefaultSharedPreferences(context).edit().remove(PREF_ACCOUNT_NAME).apply()
    }

    fun getAccountName(context: Context): String? {
        return getDefaultSharedPreferences(context).getString(PREF_ACCOUNT_NAME, null)
    }

    fun getDefaultSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            getDefaultSharedPreferencesName(context),
            getDefaultSharedPreferencesMode()
        )
    }

    private fun getDefaultSharedPreferencesName(context: Context): String {
        return context.packageName.toString() + "_preferences"
    }

    private fun getDefaultSharedPreferencesMode(): Int {
        return Context.MODE_PRIVATE
    }
}
