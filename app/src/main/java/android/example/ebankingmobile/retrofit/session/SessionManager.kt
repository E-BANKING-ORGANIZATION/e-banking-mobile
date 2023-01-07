package android.example.ebankingmobile.retrofit.session

import android.content.Context
import android.content.SharedPreferences
import android.example.ebankingmobile.R

class SessionManager(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_NAME = "user_name"
        const val USER_PASSWORD = "user_password"
        const val USER_EMAIL = "user_email"
        const val USER_ID = "user_id"
        const val USER_PHONE = "user_phone"
        const val USER_AMOUNT = "user_amount"
        const val REQUEST_ID_TEMP_TOKEN = "request_id_temp_token"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to save auth token
     */
    fun saveAmount(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_AMOUNT, token)
        editor.apply()
    }



    /**
     * Function to save auth token
     */
    fun saveUsername(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_NAME, token)
        editor.apply()
    }

    /**
     * Function to save auth token
     */
    fun savePassword(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_PASSWORD, token)
        editor.apply()
    }

    /**
     * Function to save auth token
     */
    fun saveId(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_ID, token)
        editor.apply()
    }

    /**
     * Function to save auth token
     */
    fun saveEmail(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_EMAIL, token)
        editor.apply()
    }

    fun saveRequestIdForTemporaryOtp(token: String) {
        val editor = prefs.edit()
        editor.putString(REQUEST_ID_TEMP_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to save auth token
     */
    fun savePhone(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_PHONE, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    /**
     * Function to fetch username
     */
    fun fetchUsername(): String? {
        return prefs.getString(USER_NAME, null)
    }

    /**
     * Function to fetch password
     */
    fun fetchPassword(): String? {
        return prefs.getString(USER_PASSWORD, null)
    }

    /**
     * Function to fetch password
     */
    fun fetchId(): String? {
        return prefs.getString(USER_ID, null)
    }

    /**
     * Function to fetch password
     */
    fun fetchEmail(): String? {
        return prefs.getString(USER_EMAIL, null)
    }

    /**
     * Function to fetch phone number
     */
    fun fetchPhone(): String? {
        return prefs.getString(USER_PHONE, null)
    }

    /**
     * Function to fetch phone number
     */
    fun fetchRequestId(): String? {
        return prefs.getString(REQUEST_ID_TEMP_TOKEN, null)
    }

    /**
     * Function to fetch phone number
     */
    fun fetchAmount(): String? {
        return prefs.getString(USER_AMOUNT, null)
    }

}