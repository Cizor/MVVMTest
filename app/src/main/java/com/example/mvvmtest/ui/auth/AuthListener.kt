package com.example.mvvmtest.ui.auth

import androidx.lifecycle.LiveData
import com.example.mvvmtest.data.db.entities.User

interface AuthListener {

    fun onStarted()
    fun onSuccess(user : User)
    fun onFailure(message: String)
}