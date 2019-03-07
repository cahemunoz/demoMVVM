package com.cahemunoz.demomvvm


import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cahemunoz.demomvvm.business.entities.User
import com.cahemunoz.demomvvm.presentation.user_list.viewmodels.UserListViewModel
import com.cahemunoz.demomvvm.repositories.user.RealmUserRepository
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.flowable.FlowableLimit
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserListViewModelTest {
    private val userLocalRepo = RealmUserRepository()

    @Test
    fun shouldWork() {
        val flow = Flowable.create<MutableList<User>>({ emitter ->
            val disp = userLocalRepo.findAllUsers().forEach {
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