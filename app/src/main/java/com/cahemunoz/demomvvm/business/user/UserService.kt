package com.cahemunoz.demomvvm.business.user

import com.cahemunoz.demomvvm.business.entities.User
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserService {
    fun findUserByUsername(username:String):Completable
    fun createUser(username: String, email:String):Completable
    fun findAllUsers(): Flowable<MutableList<User>>


}