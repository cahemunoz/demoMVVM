package com.cahemunoz.demomvvm.business.user.impl

import com.cahemunoz.demomvvm.business.entities.User
import com.cahemunoz.demomvvm.business.user.UserService
import com.cahemunoz.demomvvm.business.user.repositories.UserApiRepository
import com.cahemunoz.demomvvm.business.user.repositories.UserCreateRepository
import com.cahemunoz.demomvvm.business.user.repositories.UserRepository
import io.reactivex.Completable
import io.reactivex.Flowable


class UserServiceImpl(
    private val localUserRepo: UserRepository,
    private val createUserRepo: UserCreateRepository,
    private val apiUserRepo: UserApiRepository
): UserService {
    override fun findAllUsers(): Flowable<MutableList<User>> {
        return localUserRepo.findAllUsers()
    }

    override fun createUser(username: String, email: String) = createUserRepo.createUser(username, email)

    override fun findUserByUsername(username: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}