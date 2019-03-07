package com.cahemunoz.demomvvm.business.time_ticker.impl

import com.cahemunoz.demomvvm.business.time_ticker.TimerService
import io.reactivex.Flowable
import java.util.*
import java.util.concurrent.TimeUnit

class TimerServiceImpl : TimerService {
    override fun getTime(): Flowable<Date> = Flowable.interval(1L, TimeUnit.NANOSECONDS)
        .onBackpressureLatest()
        .map { Calendar.getInstance().time }
}