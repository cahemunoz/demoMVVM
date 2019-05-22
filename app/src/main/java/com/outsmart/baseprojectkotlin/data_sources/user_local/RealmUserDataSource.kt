package com.outsmart.baseprojectkotlin.data_sources.user_local

import android.util.Log
import com.outsmart.baseprojectkotlin.services._entities.User
import com.outsmart.baseprojectkotlin.services.user.repositories.UserLocalRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.Realm

class RealmUserDataSource : UserLocalRepository {

    /**
     * All observe* methods should use realmUiThread
     */
    override fun observeAllUsers(): Flowable<MutableList<User>> =
        Realm.getDefaultInstance().where(User::class.java)
            .findAllAsync()
            .asFlowable()
            .filter {
                it.isValid && it.isLoaded
            }
            .doOnCancel {
                Realm.getDefaultInstance().close()
                Log.d(RealmUserDataSource::class.java.simpleName, "Realm instance closed on thread: " + Thread.currentThread().name )
            }
            .map {
                it as MutableList<User>
            }

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