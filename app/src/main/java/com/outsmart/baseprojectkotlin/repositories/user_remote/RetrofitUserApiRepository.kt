package com.outsmart.baseprojectkotlin.repositories.user_remote

import com.outsmart.baseprojectkotlin.repositories._base.RetrofitRepositoryGenerator
import com.outsmart.baseprojectkotlin.services.entities.User
import com.outsmart.baseprojectkotlin.services.user.repositories.UserRemoteRepository
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