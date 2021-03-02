package com.example.mvvmtest.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmtest.data.db.entities.User
import com.example.mvvmtest.data.repositories.UserRepository
import com.example.mvvmtest.util.ApiException
import com.example.mvvmtest.util.Coroutines
import com.example.mvvmtest.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {
    var email : String? = null
    var password : String? = null

    var authListener : AuthListener? = null

    fun getLoggedInUSer() = repository.getUser()

    fun onLoginButtonCllicked(view : View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid Email or Password")
            return
        }
        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e : ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e : NoInternetException){
                authListener?.onFailure(e.message!!)
            }


        }
    }
}