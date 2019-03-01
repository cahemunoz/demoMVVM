package com.cahemunoz.demomvvm.presentation.user_list.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cahemunoz.demomvvm.base.ServiceGenerator
import com.cahemunoz.demomvvm.business.entities.User
import com.cahemunoz.demomvvm.business.user.UserService
import com.cahemunoz.demomvvm.business.user.impl.UserServiceImpl
import com.cahemunoz.demomvvm.repositories.user.RealmUserRepository
import com.cahemunoz.demomvvm.repositories.user_api.RetrofitUserRepository
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm
import java.util.*


class UserListViewModel(app: Application) : AndroidViewModel(app) {
    val userList = MutableLiveData<MutableList<User>>()
    val errorMessage = MutableLiveData<String>()
    val isErrorMessageVisible = MutableLiveData<Boolean>()

    private val disposes = CompositeDisposable()
    private val realm = Realm.getDefaultInstance()

    private val userService: UserService by lazy {
        UserServiceImpl(
            localUserRepo = RealmUserRepository(realm),
            createUserRepo = RealmUserRepository(realm),
            apiUserRepo = ServiceGenerator.createService(RetrofitUserRepository::class.java)
        )
    }


    init {
        observeUserList()
    }

    fun observeUserList() {
        var dispose = userService.findAllUsers().subscribe({
            userList.postValue(it)
            isErrorMessageVisible.postValue(false)
        }, { error ->
            errorMessage.postValue(error.message)
            isErrorMessageVisible.postValue(true)
        })
        disposes.add(dispose)
    }

    fun create() {
        val dispose = userService.createUser(UUID.randomUUID().toString(), "carlos@gmail.com")
            .subscribe({
                isErrorMessageVisible.postValue(false)
            } , {
                errorMessage.postValue(it.message)
                isErrorMessageVisible.postValue(true)
            })
        disposes.add(dispose)
    }


    override fun onCleared() {
        disposes.dispose()
        super.onCleared()
    }

}