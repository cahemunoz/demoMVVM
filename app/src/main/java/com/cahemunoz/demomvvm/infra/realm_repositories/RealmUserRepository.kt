package com.cahemunoz.demomvvm.infra.realm_repositories

import com.cahemunoz.demomvvm.domain.models.User
import com.cahemunoz.demomvvm.domain.users.UserRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.realm.Realm

class RealmUserRepository(private val realm: Realm) : UserRepository {
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