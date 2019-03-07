package com.cahemunoz.demomvvm.presentation.user_list.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.cahemunoz.demomvvm.business.entities.User
import com.cahemunoz.demomvvm.business.user.UserService
import com.cahemunoz.demomvvm.business.user.impl.UserServiceImpl
import com.cahemunoz.demomvvm.presentation._base.RealmRxViewModel
import com.cahemunoz.demomvvm.presentation._base.RxViewModel
import com.cahemunoz.demomvvm.repositories.user.RealmUserRepository
import com.cahemunoz.demomvvm.repositories.user_api.RetrofitUserApiRepository
import java.util.*


class UserListViewModel : RealmRxViewModel() {
    private val userService: UserService by lazy {
        UserServiceImpl(
            userLocalRepository = RealmUserRepository(realmOnUiThread),
            userRemoteRepository = RetrofitUserApiRepository()
        )
    }



    val userList: LiveData<MutableList<User>> = LiveDataReactiveStreams.fromPublisher(userService.observeUsersOrderedById())
    val errorMessage = MutableLiveData<String>()
    val isErrorMessageVisible = MutableLiveData<Boolean>()




    init {
        isErrorMessageVisible.postValue(false)
        val dispose = userService.updateUsersFromRemote().subscribe({}, {
            errorMessage.postValue(it.message)
            isErrorMessageVisible.postValue(true)
        })
        disposables.addAll(dispose)
    }


    fun create() {
        val dispose = userService.createUser(UUID.randomUUID().toString(), "carlos@gmail.com")
            .subscribe({
                isErrorMessageVisible.postValue(false)
            } , {
                errorMessage.postValue(it.message)
                isErrorMessageVisible.postValue(true)
            })
        disposables.add(dispose)
    }

}