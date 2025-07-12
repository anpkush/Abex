package com.example.abexapp.retrofit.repo

import com.example.abexapp.retrofit.ApiServices
import com.example.abexapp.retrofit.model.LoginRequest
import com.example.abexapp.retrofit.model.LoginResponse
import retrofit2.Response

class BaseRepository(private val api: ApiServices) {

    suspend fun logiRequest(loginRequest: LoginRequest) : Response<LoginResponse>{
        return api.loginRequest(loginRequest)
    }

}