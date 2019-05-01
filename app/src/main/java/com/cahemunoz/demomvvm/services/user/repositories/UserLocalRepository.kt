package com.cahemunoz.demomvvm.services.user.repositories

import com.cahemunoz.demomvvm.services.entities.User
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserLocalRepository {
    fun observeAllUsers(): Flowable<MutableList<User>>
    fun createUser(username: String, email: String): Completable
}