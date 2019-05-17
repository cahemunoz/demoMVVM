package com.outsmart.baseprojectkotlin.services.user

import com.outsmart.baseprojectkotlin.services.entities.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface UserService {
    fun findUserByUsername(username:String):Single<User>
    fun createUser(username: String, email:String): Completable
    fun observeUsersOrderedById(): Flowable<MutableList<User>>
    fun updateUsersFromRemote(): Completable
}