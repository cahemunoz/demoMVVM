package com.cahemunoz.demomvvm.business.user.repositories

import com.cahemunoz.demomvvm.business.entities.User
import io.reactivex.Single

interface UserRemoteRepository {
    fun findAllUsers(): Single<MutableList<User>>
}