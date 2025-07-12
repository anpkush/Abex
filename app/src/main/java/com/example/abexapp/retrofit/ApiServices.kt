package com.example.abexapp.retrofit

import com.example.abexapp.retrofit.model.LoginRequest
import com.example.abexapp.retrofit.model.LoginResponse
import com.example.abexapp.retrofit.model.PermitResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {

    @POST("v1/auth/signin/")
    suspend fun loginRequest(@Body request: LoginRequest): Response<LoginResponse>

    @GET("v1/workflow/newpermit")
    suspend fun getNewPermit(): Response<PermitResponse>

}