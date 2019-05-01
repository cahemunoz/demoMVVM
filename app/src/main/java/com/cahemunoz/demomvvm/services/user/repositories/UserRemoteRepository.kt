package com.cahemunoz.demomvvm.services.user.repositories

import com.cahemunoz.demomvvm.services.entities.User
import io.reactivex.Single

interface UserRemoteRepository {
    fun findAllUsers(): Single<MutableList<User>>
}