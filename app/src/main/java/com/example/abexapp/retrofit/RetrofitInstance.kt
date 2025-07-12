package com.example.abexapp.retrofit

import android.content.Context
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private const val BASE_URL = "https://test.deltafour.co/api/"
    private var retrofit: Retrofit? = null

    fun getInstance(context: Context): ApiServices {
        if (retrofit == null) {
            val sessionManager = SessionManager(context)

            val client = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(logging())
                .addInterceptor { chain ->
                    val requestBuilder = chain.request().newBuilder()
                    val token = sessionManager.fetchAuthToken()

                    Log.d("Retrofit", "Token = $token")

                    if (!token.isNullOrEmpty()) {
                        requestBuilder.addHeader("Authorization", "Bearer $token")
                    }

                    chain.proceed(requestBuilder.build())
                }
                .authenticator(TokenAuthenticator(sessionManager, context))
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!.create(ApiServices::class.java)
    }

    private fun logging(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor { message ->
            Log.d("HTTP", message)
        }
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }
}