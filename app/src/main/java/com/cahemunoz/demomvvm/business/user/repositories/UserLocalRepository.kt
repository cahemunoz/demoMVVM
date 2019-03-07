package com.cahemunoz.demomvvm.business.user.repositories

import com.cahemunoz.demomvvm.business.entities.User
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserLocalRepository {
    fun findAllUsers(): Flowable<MutableList<User>>
    fun createUser(sername: String, email: String): Completable
}