package com.cahemunoz.demomvvm.presentation.login.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahemunoz.demomvvm.business.time_ticker.TimerService
import com.cahemunoz.demomvvm.business.time_ticker.impl.TimerServiceImpl
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit


class LoginViewModel : ViewModel() {
    private val disposables = CompositeDisposable()


    val currentDate = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    val isErrorVisible = MutableLiveData<Boolean>()

    private val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS")


    private val timerService: TimerService = TimerServiceImpl()


    init {
        setUpTimer()
    }


    private fun setUpTimer() {
        val disposable = timerService.getTime()
            .map { formatter.format(it) }
            .subscribe({
                currentDate.postValue(it)
            },{ processFailure(it)})
        disposables.add(disposable)
    }

    private fun processFailure(failure: Throwable) {
        errorMessage.postValue(failure.message)
        isErrorVisible.postValue(true)
        val d = Completable.complete()
            .delay(1L, TimeUnit.SECONDS)
            .subscribe {
                isErrorVisible.postValue(false)
            }
        disposables.add(d)
    }



    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

}