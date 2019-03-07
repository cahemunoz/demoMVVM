package com.cahemunoz.demomvvm.business.time_ticker

import io.reactivex.Flowable
import java.util.*

interface TimerService {
    fun getTime(): Flowable<Date>
}
