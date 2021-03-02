package com.example.mvvmtest.data.repositories

import com.example.mvvmtest.data.db.AppDatabase
import com.example.mvvmtest.data.db.entities.User
import com.example.mvvmtest.data.network.MyApi
import com.example.mvvmtest.data.network.SafeApiRequest
import com.example.mvvmtest.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest(){
    suspend fun userLogin(email : String, password : String) : AuthResponse{
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun saveUser(user : User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}