package com.cahemunoz.demomvvm.repositories.user

import com.cahemunoz.demomvvm.business.entities.User
import com.cahemunoz.demomvvm.business.user.repositories.UserCreateRepository
import com.cahemunoz.demomvvm.business.user.repositories.UserRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.realm.Realm

class RealmUserRepository(private val realm: Realm) : UserRepository, UserCreateRepository {
    override fun findAllUsers(): Flowable<MutableList<User>> {
        return realm.where(User::class.java)
                .findAllAsync()
                .asFlowable()
                .map { it as MutableList<User> }

    }

    override fun createUser(username: String, email: String): Completable = Completable.create { emitter ->
        realm.executeTransactionAsync({ realm ->
            val user = realm.createObject(User::class.java)
            user.username = username
            user.email = email
        }, {
            emitter.onComplete()
        }, { error ->
            emitter.onError(error)
        })
    }
}