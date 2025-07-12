package com.example.abexapp.retrofit.model

data class LoginRequest(
    val username: String,
    val password: String,
    val deviceId: String,
    val appVersion: String,
    val web: Boolean
)