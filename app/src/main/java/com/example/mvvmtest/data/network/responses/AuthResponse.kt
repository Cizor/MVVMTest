package com.example.mvvmtest.data.network.responses

import com.example.mvvmtest.data.db.entities.User

data class AuthResponse (
    val isSuccessful: Boolean?,
    val message : String?,
    val user : User?
    )