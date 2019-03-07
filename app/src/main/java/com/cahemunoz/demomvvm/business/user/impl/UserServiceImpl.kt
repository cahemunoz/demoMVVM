package com.cahemunoz.demomvvm.business.user.impl

import com.cahemunoz.demomvvm.business.entities.User
import com.cahemunoz.demomvvm.business.user.UserService
import com.cahemunoz.demomvvm.business.user.repositories.UserRemoteRepository
import com.cahemunoz.demomvvm.business.user.repositories.UserLocalRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.concurrent.TimeUnit


class UserServiceImpl(
    private val userLocalRepository: UserLocalRepository,
    private val userRemoteRepository: UserRemoteRepository
) : UserService {

    /**
     * All methods with observe prefix should return a Flowable
     */
    override fun observeUsersOrderedById(): Flowable<MutableList<User>> {
        return userLocalRepository.observeAllUsers()
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
        }.ignoreElement()


    override fun createUser(username: String, email: String):Completable {
        return userLocalRepository.createUser(username, email)
            .subscribeOn(Schedulers.computation())
    }

    override fun findUserByUsername(username: String): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}