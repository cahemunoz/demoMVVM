package com.outsmart.baseprojectkotlin


import androidx.test.annotation.UiThreadTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.outsmart.baseprojectkotlin.data_sources.user_local.RealmUserDataSource
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserListViewModelTest {
    private val userLocalRepo = RealmUserDataSource()

    @UiThreadTest
    @Test
    fun shouldWork() {
        val dispose = userLocalRepo.observeAllUsers()
            .subscribe {
                println(it.size)
            }


        Thread.sleep(5000)
        dispose.dispose()
    }
}