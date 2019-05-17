package com.outsmart.baseprojectkotlin.services.time_ticker

import io.reactivex.Flowable
import java.util.*

interface TimerService {
    fun getTime(): Flowable<Date>
}
