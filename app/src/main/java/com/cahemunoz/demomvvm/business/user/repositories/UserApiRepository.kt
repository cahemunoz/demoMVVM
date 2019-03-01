package com.cahemunoz.demomvvm.business.user.repositories

import com.cahemunoz.demomvvm.business.entities.User
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserApiRepository {
    fun findAllUsers(): Flowable<MutableList<User>>
}