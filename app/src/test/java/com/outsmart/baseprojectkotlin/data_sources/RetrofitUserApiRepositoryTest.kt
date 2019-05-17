package com.outsmart.baseprojectkotlin.data_sources

import com.outsmart.baseprojectkotlin.data_sources.user_remote.RetrofitUserApiDataSource
import org.junit.Test

class RetrofitUserApiRepositoryTest {
    private val userApiRepository = RetrofitUserApiDataSource()

    @Test
    fun shouldRetrieveUsers() {
        val flow = userApiRepository.findAllUsers()
        val users = flow.blockingGet()
        users.forEach {
            println("username: ${it.username}  email: ${it.email}")
        }
    }

}