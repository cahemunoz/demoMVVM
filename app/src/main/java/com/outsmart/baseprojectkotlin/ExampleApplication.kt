package com.outsmart.baseprojectkotlin

import android.app.Application
import com.outsmart.baseprojectkotlin.di.userModule
import com.outsmart.baseprojectkotlin.util_functions.executionTime
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