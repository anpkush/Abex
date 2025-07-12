package com.example.abexapp.retrofit

import android.content.Context
import android.content.Intent
import com.example.abexapp.LoginActivity
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val sessionManager: SessionManager,
    private val context: Context
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        if (response.code == 401) {
            handleUnauthorized()
            return null
        }

        return null
    }

    private fun handleUnauthorized() {
        sessionManager.clearToken()

        val intent = Intent(context, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)
    }
}