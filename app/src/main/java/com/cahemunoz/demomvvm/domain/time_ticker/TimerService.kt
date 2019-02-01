package com.cahemunoz.demomvvm.domain.time_ticker

import io.reactivex.Flowable
import java.util.*

open interface TimerService {
    fun getTime(): Flowable<Date>
}
