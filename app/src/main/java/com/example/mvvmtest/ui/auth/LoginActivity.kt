package com.example.mvvmtest.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmtest.R
import com.example.mvvmtest.databinding.ActivityLoginBinding
import com.example.mvvmtest.util.hide
import com.example.mvvmtest.util.show
import com.example.mvvmtest.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener = this

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            progress_bar.hide()
            toast(it)
        })
    }

    override fun onFailure(message : String) {
        progress_bar.hide()
        toast(message)
    }

}