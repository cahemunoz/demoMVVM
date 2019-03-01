package com.cahemunoz.demomvvm.business.user.repositories

import io.reactivex.Completable

interface UserCreateRepository {
    fun createUser(username: String, email: String): Completable
}