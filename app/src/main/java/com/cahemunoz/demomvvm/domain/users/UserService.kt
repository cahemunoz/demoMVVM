package com.cahemunoz.demomvvm.domain.users

import com.cahemunoz.demomvvm.domain.models.User
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserService {
    fun findUserByUsername(username:String):Completable
    fun createUser(username: String, email:String):Completable
    fun findAllUsers(): Flowable<MutableList<User>>

}