package com.cahemunoz.demomvvm

import android.app.Application
import com.cahemunoz.demomvvm.di.userModule
import com.cahemunoz.demomvvm.util_functions.executionTime
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.android.startKoin

/**
 * I thought leave setup realm responsibility to each application
 */
class ExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)

        executionTime {
            startKoin(this, listOf(userModule))
        }
    }


}