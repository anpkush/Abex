package com.example.abexapp.retrofit

import android.content.Context

class SessionManager(context: Context) {

    private var sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    fun saveAuthToken(token: String) {
        val editor = sharedPref.edit()
        editor.putString(USER_TOKEN,token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return sharedPref.getString(USER_TOKEN, null)
    }

    fun clearToken() {
        sharedPref.edit().remove(USER_TOKEN).apply()
    }
}