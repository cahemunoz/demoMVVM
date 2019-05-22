package com.outsmart.baseprojectkotlin.data_sources.user_local

import android.content.Context
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.outsmart.baseprojectkotlin.services._entities.User
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Predicate
import io.reactivex.observers.BaseTestConsumer
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmConfiguration
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RealmUserDataSourceTest {

    private val userLocalDataSource = RealmUserDataSource()

    @Before
    fun setup() {
        val appContext = ApplicationProvider.getApplicationContext<Context>()
        Realm.init(appContext)
        Realm.removeDefaultConfiguration()
        val config = RealmConfiguration.Builder().inMemory().build()
        Realm.setDefaultConfiguration(config)
    }


    @UiThreadTest
    @Test
    fun should_observe_users_from_local_storage() {
        val user = User()
        user.username = "cahemunoz"
        user.email = "cahemunoz@gmail.com"

        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.insertOrUpdate(user)
        realm.commitTransaction()

        var fromDB = userLocalDataSource.observeAllUsers()
            .map {
                it.first().username
            }
            .doOnNext {
                println(it)
            }
            .subscribe {
                print(it)
                Assert.assertEquals("cahemunozz", it)
            }
    }
}