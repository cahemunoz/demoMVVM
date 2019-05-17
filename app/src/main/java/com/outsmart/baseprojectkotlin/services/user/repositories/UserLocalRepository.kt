package com.outsmart.baseprojectkotlin.services.user.repositories

import com.outsmart.baseprojectkotlin.services._entities.User
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserLocalRepository {
    fun observeAllUsers(): Flowable<MutableList<User>>
    fun createUser(username: String, email: String): Completable
}