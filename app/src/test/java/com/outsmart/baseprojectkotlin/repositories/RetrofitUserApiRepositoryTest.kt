package com.outsmart.baseprojectkotlin.repositories

import com.outsmart.baseprojectkotlin.repositories.user_remote.RetrofitUserApiRepository
import org.junit.Test

class RetrofitUserApiRepositoryTest {
    private val userApiRepository = RetrofitUserApiRepository()

    @Test
    fun shouldRetrieveUsers() {
        val flow = userApiRepository.findAllUsers()
        val users = flow.blockingGet()
        users.forEach {
            println("username: ${it.username}  email: ${it.email}")
        }
    }

}