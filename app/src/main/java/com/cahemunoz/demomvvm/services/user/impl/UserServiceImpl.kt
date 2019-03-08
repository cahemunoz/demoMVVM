package com.cahemunoz.demomvvm.services.user.impl

import com.cahemunoz.demomvvm.services.entities.User
import com.cahemunoz.demomvvm.services.user.UserService
import com.cahemunoz.demomvvm.services.user.repositories.UserRemoteRepository
import com.cahemunoz.demomvvm.services.user.repositories.UserLocalRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit


class UserServiceImpl(
    private var userLocalRepository: UserLocalRepository,
    private var userRemoteRepository: UserRemoteRepository) : UserService {

    /**
     * All methods with observe prefix should return a Flowable
     */
    override fun observeUsersOrderedById(): Flowable<MutableList<User>> {
        return userLocalRepository.observeAllUsers().
                doOnNext { throw RuntimeException("Errouuuu") }
    }

    override fun updateUsersFromRemote(): Completable = userRemoteRepository.findAllUsers()
        .subscribeOn(Schedulers.io())
        .delay(5L, TimeUnit.SECONDS)
        .doOnSuccess { apiUsers ->
            apiUsers.forEach {
                if (it.username != null && it.email != null) {
                    createUser(it.username!!, it.email!!).subscribe()
                } else
                    throw Exception("Usuario incompleto")
            }
        }?.ignoreElement() ?: Completable.create { it.onComplete() }


    override fun createUser(username: String, email: String): Completable {
        return userLocalRepository.createUser(username, email)
            .subscribeOn(Schedulers.computation())
    }

    override fun findUserByUsername(username: String): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}