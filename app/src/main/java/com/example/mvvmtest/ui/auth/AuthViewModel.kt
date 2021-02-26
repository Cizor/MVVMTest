package com.example.mvvmtest.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    var email : String? = null
    var password : String? = null

    var authListener : AuthListener? = null

    fun onLoginButtonCllicked(view : View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid Email or Password")
            return
        }
        authListener?.onSuccess()
    }
}