package com.cahemunoz.demomvvm.domain.users

import com.cahemunoz.demomvvm.domain.models.User
import io.reactivex.Completable
import io.reactivex.Flowable

class UserServiceImpl(
    private val userRepo: UserRepository
): UserService {
    override fun findAllUsers(): Flowable<MutableList<User>> = userRepo.findAllUsers()

    override fun createUser(username: String, email: String) = userRepo.createUser(username, email)

    override fun findUserByUsername(username: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}