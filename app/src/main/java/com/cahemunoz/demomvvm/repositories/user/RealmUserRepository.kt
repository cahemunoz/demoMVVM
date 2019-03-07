package com.cahemunoz.demomvvm.repositories.user

import com.cahemunoz.demomvvm.business.entities.User
import com.cahemunoz.demomvvm.business.user.repositories.UserLocalRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.realm.Realm

class RealmUserRepository : UserLocalRepository {
    override fun findAllUsers(): Flowable<MutableList<User>> {
        Realm.getDefaultInstance().use { realm ->
            val flow = realm.where(User::class.java)
                .findAllAsync()
                .asFlowable()
                .map { it as MutableList<User> }
                .doOnCancel {
                    println("Fechouuuuuuu")
                }
            return flow
        }
    }

    override fun createUser(username: String, email: String): Completable = Completable.create {
        Realm.getDefaultInstance().use {
            it.executeTransactionAsync {realm ->
                val user = realm.createObject(User::class.java, username)
                user.email = email
            }
        }
    }
}