package com.outsmart.baseprojectkotlin.services.user.repositories

import com.outsmart.baseprojectkotlin.services.entities.User
import io.reactivex.Single

interface UserRemoteRepository {
    fun findAllUsers(): Single<MutableList<User>>
}