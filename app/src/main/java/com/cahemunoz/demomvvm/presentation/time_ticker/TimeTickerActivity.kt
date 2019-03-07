package com.cahemunoz.demomvvm.presentation.time_ticker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.cahemunoz.demomvvm.presentation.time_ticker.viewmodels.TimeTickerViewModel
import com.cahemunoz.demomvvm.R
import com.cahemunoz.demomvvm.databinding.ActivityTimeTickerBinding

class TimeTickerActivity : AppCompatActivity() {


    private val loginViewModel: TimeTickerViewModel by lazy { ViewModelProviders.of(this).get(
        TimeTickerViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityTimeTickerBinding>(this,
            R.layout.activity_time_ticker
        )
        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel

    }
}
