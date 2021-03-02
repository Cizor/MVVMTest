package com.example.mvvmtest.ui.auth

import android.content.Intent
import com.example.mvvmtest.util.show
import com.example.mvvmtest.util.hide
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmtest.R
import com.example.mvvmtest.data.db.entities.User
import com.example.mvvmtest.databinding.ActivityLoginBinding
import com.example.mvvmtest.ui.home.HomeActivity
import com.example.mvvmtest.util.snackbar
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {
    lateinit var binding : ActivityLoginBinding
    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUSer().observe(this, { user ->
            if(user != null){
                Intent(this, HomeActivity::class.java).also{
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

    }

    override fun onStarted() {
        binding.progressBar.show()
    }

    override fun onSuccess(user : User) {
        binding.progressBar.hide()
    }

    override fun onFailure(message : String) {
        binding.progressBar.hide()
        binding.rootLayout.snackbar(message)
    }

}