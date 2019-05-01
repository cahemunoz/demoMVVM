package com.cahemunoz.demomvvm.repositories.user

import com.cahemunoz.demomvvm.services.entities.User
import com.cahemunoz.demomvvm.services.user.repositories.UserLocalRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.realm.Realm

class RealmUserRepository(private val realmUiThread: Realm) : UserLocalRepository {

    /**
     * All observe* methods should use realmUiThread
     */
    override fun observeAllUsers(): Flowable<MutableList<User>> =
        realmUiThread.where(User::class.java)
            .findAllAsync()
            .asFlowable()
            .map { it as MutableList<User> }

    /**
     * To make changes on database use a new realm instance (create, update, delete)
     * don't forget close the instance.
     * using instance.use{} auto closes the instance when the process ends.
     */
    override fun createUser(username: String, email: String): Completable = Completable.create { emitter ->
        Realm.getDefaultInstance().use {
            it.executeTransactionAsync { realm ->
                val user = User().apply {
                    this.username = username
                    this.email = email
                }
                realm.copyToRealmOrUpdate(user)
                emitter.onComplete()
            }
        }
    }
}