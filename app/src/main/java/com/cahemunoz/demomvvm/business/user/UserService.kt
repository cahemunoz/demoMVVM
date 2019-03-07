package com.cahemunoz.demomvvm.business.user

import com.cahemunoz.demomvvm.business.entities.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface UserService {
    fun findUserByUsername(username:String):Single<User>
    fun createUser(username: String, email:String): Completable
    fun observeUsersOrderedById(): Flowable<MutableList<User>>
    fun updateUsersFromRemote(): Completable
}