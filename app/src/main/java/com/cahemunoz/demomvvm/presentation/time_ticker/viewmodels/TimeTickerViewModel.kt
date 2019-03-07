package com.cahemunoz.demomvvm.presentation.time_ticker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.cahemunoz.demomvvm.business.time_ticker.TimerService
import com.cahemunoz.demomvvm.business.time_ticker.impl.TimerServiceImpl
import com.cahemunoz.demomvvm.presentation._base.RxViewModel
import java.text.SimpleDateFormat


class TimeTickerViewModel : RxViewModel() {

    private val timerService: TimerService = TimerServiceImpl()
    private val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS")


    val currentDate: LiveData<String> = LiveDataReactiveStreams.fromPublisher(
        timerService.getTime()
            .map { formatter.format(it) }
    )


}