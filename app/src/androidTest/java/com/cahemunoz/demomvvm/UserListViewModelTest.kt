package com.cahemunoz.demomvvm


import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cahemunoz.demomvvm.services.entities.User
import com.cahemunoz.demomvvm.repositories.user.RealmUserRepository
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserListViewModelTest {
    private val userLocalRepo = RealmUserRepository()

    @Test
    fun shouldWork() {
        val flow = Flowable.create<MutableList<User>>({ emitter ->
            val disp = userLocalRepo.observeAllUsers().forEach {
                emitter.onNext(it)
            }
            emitter.setDisposable(disp)
        }, BackpressureStrategy.LATEST)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                println(it)
            }


        Thread.sleep(5000)
        flow.dispose()
    }
}