package kr.hs.dgsw.smartschool.data.database.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceManager {

    private const val PREF_ACCOUNT_NAME = "account"
    private const val PREF_DARK_MODE = "pref_daylight"
    private const val PREF_IS_LOGIN = "is_login"

    fun getIsSignIn(context: Context): Boolean {
        return getDefaultSharedPreferences(context).getBoolean(PREF_IS_LOGIN, false)
    }

    fun signIn(context: Context){
        getDefaultSharedPreferences(context).edit().putBoolean(PREF_IS_LOGIN, true).apply()
    }

    fun signOut(context: Context){
        getDefaultSharedPreferences(context).edit().putBoolean(PREF_IS_LOGIN, false).apply()
    }

    fun getDayLight(context: Context, defaultString: String): String {
        return getDefaultSharedPreferences(context).getString(PREF_DARK_MODE, defaultString)!!
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