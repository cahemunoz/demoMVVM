package com.cahemunoz.demomvvm.presentation.user_list.viewmodels

import androidx.lifecycle.MutableLiveData
import com.cahemunoz.demomvvm.business.entities.User
import com.cahemunoz.demomvvm.business.user.UserService
import com.cahemunoz.demomvvm.business.user.impl.UserServiceImpl
import com.cahemunoz.demomvvm.presentation._base.RealmRxViewModel
import com.cahemunoz.demomvvm.repositories.user.RealmUserRepository
import com.cahemunoz.demomvvm.repositories.user_api.RetrofitUserApiRepository
import java.util.*


class UserListViewModel : RealmRxViewModel() {
    val userList = MutableLiveData<MutableList<User>>()
    val errorMessage = MutableLiveData<String>()
    val isErrorMessageVisible = MutableLiveData<Boolean>()

    private val userService: UserService by lazy {
        UserServiceImpl(
            userLocalRepository = RealmUserRepository(),
            userRemoteRepository = RetrofitUserApiRepository()
        )
    }


    init {
        observeUserList()
    }

    private fun observeUserList() {
        var dispose = userService.observeUsersOrderedById().subscribe({
            userList.postValue(it)
            isErrorMessageVisible.postValue(false)
        }, { error ->
            errorMessage.postValue(error.message)
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