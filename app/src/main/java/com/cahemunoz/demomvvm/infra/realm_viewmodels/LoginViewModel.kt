package com.cahemunoz.demomvvm.infra.realm_viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahemunoz.demomvvm.domain.time_ticker.TimerService
import com.cahemunoz.demomvvm.domain.time_ticker.impl.TimerServiceImpl
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat



class LoginViewModel : ViewModel() {
    private val disposables = CompositeDisposable()


    val currentDate = MutableLiveData<String>()

    private val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS")


    private val timerService: TimerService = TimerServiceImpl()


    init {
        setUpTimer()
    }


    private fun setUpTimer() {
        val disposable = timerService.getTime()
            .map { formatter.format(it) }
            .subscribe {
                currentDate.postValue(it)
            }
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

}