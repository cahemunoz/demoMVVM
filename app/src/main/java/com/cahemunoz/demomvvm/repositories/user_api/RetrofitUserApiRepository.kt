package com.cahemunoz.demomvvm.repositories.user_api

import com.cahemunoz.demomvvm.repositories._base.ServiceGenerator
import com.cahemunoz.demomvvm.business.entities.User
import com.cahemunoz.demomvvm.business.user.repositories.UserRemoteRepository
import io.reactivex.Single


// Mappers should appear here
class RetrofitUserApiRepository: UserRemoteRepository {
    private val userApi = ServiceGenerator.createService(RetrofitUserApi::class.java)


    override fun findAllUsers(): Single<MutableList<User>> {
        return userApi.findAllUsers()
            .map { githubUsers ->
                githubUsers.map {
                    it.toUser()
                }.toMutableList()
            }
    }

}