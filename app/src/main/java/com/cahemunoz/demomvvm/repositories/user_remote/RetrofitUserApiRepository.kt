package com.cahemunoz.demomvvm.repositories.user_remote

import com.cahemunoz.demomvvm.repositories._base.RetrofitRepositoryGenerator
import com.cahemunoz.demomvvm.services.entities.User
import com.cahemunoz.demomvvm.services.user.repositories.UserRemoteRepository
import io.reactivex.Single


/**
 * Mapper should be here
 */
class RetrofitUserApiRepository: UserRemoteRepository {
    private val userApi = RetrofitRepositoryGenerator.createRepository(RetrofitUserApi::class.java)


    override fun findAllUsers(): Single<MutableList<User>> {
        return userApi.findAllUsers()
            .map { githubUsers ->
                githubUsers.map {
                    it.toUser()
                }.toMutableList()
            }
    }

}