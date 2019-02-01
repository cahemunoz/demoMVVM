package com.cahemunoz.demomvvm.domain.users

import com.cahemunoz.demomvvm.domain.models.User
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserRepository {
    fun findAllUsers(): Flowable<MutableList<User>>

    fun createUser(username: String, email: String): Completable
}