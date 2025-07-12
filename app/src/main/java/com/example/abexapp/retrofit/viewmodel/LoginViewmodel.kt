package com.example.abexapp.retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.abexapp.retrofit.RetrofitInstance
import com.example.abexapp.retrofit.model.LoginRequest
import com.example.abexapp.retrofit.model.LoginResponse
import com.example.abexapp.retrofit.repo.BaseRepository
import retrofit2.Response
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val api = RetrofitInstance.getInstance(application)
    private val repository = BaseRepository(api)

    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> = _loginResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun login(userName: String, password: String, deviceId: String, appVersion: String, web: Boolean) {
        viewModelScope.launch {
            try {
                val request = LoginRequest(userName, password, deviceId, appVersion, web)
                val response: Response<LoginResponse> = repository.logiRequest(request)

                if (response.isSuccessful) {
                    _loginResponse.postValue(response.body())
                } else {
                    _error.postValue("Login failed: ${response.errorBody()?.string() ?: "Unknown error"}")
                }
            } catch (e: Exception) {
                _error.postValue("An error occurred: ${e.localizedMessage}")
            }
        }
    }
}
