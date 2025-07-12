package com.example.abexapp.retrofit.model

data class LoginResponse(
    val id: Int,
    val username: String,
    val email: Any,
    val department: Any,
    val designation: Any,
    val empId: Any,
    val workAtHeightOrConfinedSpacePass: Any,
    val isolationPoint: Any,
    val phoneNumber: Any,
    val companyAccountId: Int,
    val company_account_name: String,
    val roles: List<String>,
    val customRoles: List<String>,
    val userHierarchy: Any,
    val accessToken: String,
    val tokenType: String
)