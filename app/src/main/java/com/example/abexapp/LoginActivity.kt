package com.example.abexapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.provider.Settings
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.abexapp.databinding.ActivityLoginBinding
import com.example.abexapp.databinding.ActivityMainBinding
import com.example.abexapp.retrofit.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        
        val deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        val appVersion = "1.0"
        val web = false

        binding.btLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (validateInputs(username, password)) {
                viewModel.login(username, password, deviceId, appVersion, web)
            }
        }

        viewModel.loginResponse.observe(this) { response ->
            if (response?.accessToken != null && response.tokenType == "Bearer") {

                // Save token
                val token = "${response.tokenType} ${response.accessToken}"
                val sharedPrefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
                sharedPrefs.edit().putString("token", token).apply()

                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.error.observe(this) { errorMsg ->
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInputs(username: String, password: String): Boolean {
        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter username.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter password.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
