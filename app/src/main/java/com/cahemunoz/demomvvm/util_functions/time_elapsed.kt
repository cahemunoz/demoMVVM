package com.cahemunoz.demomvvm.util_functions

import android.util.Log

inline fun executionTime(toMeasure: () -> Unit) {
    val start = System.currentTimeMillis()
    toMeasure()
    val elapsed = System.currentTimeMillis() - start
    Log.d("ExecutionTime", "Time elapsed $elapsed millis")
}