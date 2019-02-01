package com.cahemunoz.demomvvm.infra.realm_viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cahemunoz.demomvvm.domain.models.User
import com.cahemunoz.demomvvm.domain.users.UserService
import com.cahemunoz.demomvvm.domain.users.UserServiceImpl
import com.cahemunoz.demomvvm.infra.realm_repositories.RealmUserRepository
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm
import java.util.*


class UserListViewModel(app: Application) : AndroidViewModel(app) {
    val userList = MutableLiveData<MutableList<User>>()
    val errorMessage = MutableLiveData<String>()
    val isErrorMessageVisible = MutableLiveData<Boolean>()

    private val disposes = CompositeDisposable()
    private val realm = Realm.getDefaultInstance()

    private val userService: UserService by lazy { UserServiceImpl(RealmUserRepository(realm)) }


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