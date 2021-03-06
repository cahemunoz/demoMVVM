package com.cahemunoz.demomvvm.presentation.screens.user_list.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.cahemunoz.demomvvm.services.entities.User
import com.cahemunoz.demomvvm.services.user.UserService
import com.cahemunoz.demomvvm.presentation._base.RxViewModel
import java.util.*


class UserListViewModel(val userService: UserService) : RxViewModel() {

    val userList: LiveData<MutableList<User>> = LiveDataReactiveStreams.fromPublisher(
        userService.observeUsersOrderedById()
            .doOnError(this::onUserListError)
            .onErrorReturnItem(mutableListOf())
    )
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

    private fun onUserListError(t: Throwable) {
        errorMessage.postValue(t.message)
        isErrorMessageVisible.postValue(true)
    }


    fun create() {
        val dispose = userService.createUser(UUID.randomUUID().toString(), "carlos@gmail.com")
            .subscribe({
                isErrorMessageVisible.postValue(false)
            }, {
                errorMessage.postValue(it.message)
                isErrorMessageVisible.postValue(true)
            })
        disposables.add(dispose)
    }

}
