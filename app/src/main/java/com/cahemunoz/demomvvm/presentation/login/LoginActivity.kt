package com.cahemunoz.demomvvm.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.cahemunoz.demomvvm.presentation.login.viewmodels.LoginViewModel
import com.cahemunoz.demomvvm.R
import com.cahemunoz.demomvvm.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {


    private val loginViewModel: LoginViewModel by lazy { ViewModelProviders.of(this).get(
        LoginViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this,
            R.layout.activity_login
        )
        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel

    }
}