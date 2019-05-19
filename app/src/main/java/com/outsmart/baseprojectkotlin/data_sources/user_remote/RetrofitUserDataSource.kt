package com.outsmart.baseprojectkotlin.data_sources.user_remote

import com.outsmart.baseprojectkotlin.data_sources._base.RetrofitRepositoryGenerator
import com.outsmart.baseprojectkotlin.services._entities.User
import com.outsmart.baseprojectkotlin.services.user.repositories.UserRemoteRepository
import io.reactivex.Single


/**
 * Mapper should be here
 */
class RetrofitUserDataSource(
    private var userApi: RetrofitUserApi
): UserRemoteRepository {

    override fun findAllUsers(): Single<MutableList<User>> {
        return userApi.findAllUsers()
            .map { githubUsers ->
                githubUsers.map {
                    it.toUser()
                }.toMutableList()
            }
    }

}